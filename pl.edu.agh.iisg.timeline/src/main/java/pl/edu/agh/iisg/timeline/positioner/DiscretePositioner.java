package pl.edu.agh.iisg.timeline.positioner;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import pl.edu.agh.iisg.timeline.VisualConstants;
import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.AxisElement;
import pl.edu.agh.iisg.timeline.model.ISeparatorFactory;
import pl.edu.agh.iisg.timeline.model.Separator;

import com.google.common.collect.TreeMultimap;

/**
 * Positioner for the discrete timeline. It positions the elements in the groups representing the same time on the axis.
 *
 * XXX [leszko] if it works too slow, it can be optimised by using only arrays in the logic.
 *
 * @author leszko
 *
 */
public class DiscretePositioner implements IPositioner {

    public static final int SEPARATOR_GAP = VisualConstants.SEPARATOR_HEIGHT + VisualConstants.SEPARATOR_MARGIN_BOTTOM;

    private long start = 0L;

    private long interval;

    private final IMeasurer measurer;

    private final ISeparatorFactory separatorFactory;

    private TreeMap<Integer, Separator> separators = new TreeMap<>();

    private Map<Long, Integer> separatorPosition = new HashMap<>();

    private Map<AxisElement, Integer> positions = new HashMap<>();

    private TreeMultimap<Integer, AxisElement> elementsByPosition = TreeMultimap.create();

    public DiscretePositioner(long interval, IMeasurer measurer, ISeparatorFactory separatorFactory) {
        this.interval = interval;
        this.measurer = measurer;
        this.separatorFactory = separatorFactory;
    }

    @Override
    public void init(Collection<AxisElement> elements) {
        long[] dates = extractDates(elements);
        SortedMap<Long, Integer> groups = groupByIntervals(dates);
        positionInGroups(elements, groups);
    }

    private long[] extractDates(Collection<AxisElement> elements) {
        long[] res = new long[elements.size()];
        int i = 0;
        for (AxisElement e : elements) {
            res[i++] = e.getDate();
        }
        return res;
    }

    private SortedMap<Long, Integer> groupByIntervals(long[] dates) {
        SortedMap<Long, Integer> groups = new TreeMap<>();
        if (dates.length > 0) {
            long intStart = dates[0] - ((dates[0] - start) % interval);
            int group = 0;
            for (long date : dates) {
                if (date < intStart + interval) {
                    group++;
                } else {
                    groups.put(intStart, group);
                    group = 1;
                    intStart = date - ((date - intStart) % interval);
                }
            }
            groups.put(intStart, group);
        }
        return groups;
    }

    private void positionInGroups(Collection<AxisElement> elements, SortedMap<Long, Integer> groups) {
        Map<Axis, Integer> pos = new HashMap<>();
        int posMax = 0;
        Iterator<AxisElement> itr = elements.iterator();
        for (Map.Entry<Long, Integer> group : groups.entrySet()) {
            long date = group.getKey();
            int n = group.getValue();
            addSeparator(posMax, date);
            posMax += SEPARATOR_GAP;
            for (int i = 0; i < n; i++) {
                AxisElement element = itr.next();
                Axis axis = element.getAxis();
                Integer posForAxis = pos.get(axis);
                if (posForAxis == null || posForAxis < posMax) {
                    posForAxis = posMax;
                }
                positions.put(element, posForAxis);
                elementsByPosition.put(posForAxis, element);
                posForAxis += measurer.getHeightOf(element);
                pos.put(axis, posForAxis);
            }
            posMax = Collections.max(pos.values());
        }
    }

    private void addSeparator(int position, long date) {
        int sepPos = position;
        separators.put(sepPos, separatorFactory.newSeparator(date));
        separatorPosition.put(date, sepPos);
    }

    public void setGranulation(long granulation) {
        this.interval = granulation;
    }

    public void setStart(long start) {
        this.start = start;
    }

    @Override
    public int getPositionOf(AxisElement element) {
        return positions.get(element);
    }

    @Override
    public Collection<Separator> getSeparatorsByPosition(int start, int end) {
        return separators.subMap(start, end).values();
    }

    @Override
    public int getPositionOfSeparator(Separator separator) {
        return separatorPosition.get(separator.getValue());
    }

    @Override
    public Collection<AxisElement> getElementsByPosition(int start, int end) {
        return flatten(elementsByPosition.asMap().subMap(start, end).values());
    }

    private Collection<AxisElement> flatten(Collection<Collection<AxisElement>> values) {
        List<AxisElement> result = new LinkedList<>();
        for (Collection<AxisElement> col : values) {
            result.addAll(col);
        }
        return result;
    }
}
