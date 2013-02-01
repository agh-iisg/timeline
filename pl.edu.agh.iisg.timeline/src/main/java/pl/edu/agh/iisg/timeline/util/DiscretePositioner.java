package pl.edu.agh.iisg.timeline.util;

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
import pl.edu.agh.iisg.timeline.model.Element;
import pl.edu.agh.iisg.timeline.model.Separator;

import com.google.common.collect.TreeMultimap;

/**
 * Positioner for the discrete timeline. It positions the elements in the groups representing the same time on the axis. The interval of the
 * separators is constant.
 *
 * @author AGH CAST Team
 *
 */
public class DiscretePositioner implements IPositioner {

    public static final int SEPARATOR_GAP = VisualConstants.SEPARATOR_MARGIN_TOP_BOTTOM + VisualConstants.SEPARATOR_HEIGHT;

    private long start = 0L;

    private long interval;

    private final IElementMeasurer measurer;

    private final ISeparatorFactory separatorFactory;

    private TreeMap<Integer, Separator> separators = new TreeMap<>();

    private Map<Long, Integer> separatorPosition = new HashMap<>();

    private Map<Element, Integer> positions = new HashMap<>();

    private TreeMultimap<Integer, Element> elementsByPosition = TreeMultimap.create();

    private int maxPosition;

    public DiscretePositioner(long interval, IElementMeasurer measurer, ISeparatorFactory separatorFactory) {
        this.interval = interval;
        this.measurer = measurer;
        this.separatorFactory = separatorFactory;
    }

    @Override
    public void init(Collection<Element> elements) {
        long[] dates = extractDates(elements);
        SortedMap<Long, Integer> groups = groupByIntervals(dates);
        positionInGroups(elements, groups);
    }

    private long[] extractDates(Collection<Element> elements) {
        long[] res = new long[elements.size()];
        int i = 0;
        for (Element e : elements) {
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

    private void positionInGroups(Collection<Element> elements, SortedMap<Long, Integer> groups) {
        Map<Axis, Integer> pos = new HashMap<>();
        int posMax = 0;
        Iterator<Element> itr = elements.iterator();
        for (Map.Entry<Long, Integer> group : groups.entrySet()) {
            long date = group.getKey();
            int n = group.getValue();
            addSeparator(posMax, date);
            posMax += SEPARATOR_GAP;
            for (int i = 0; i < n; i++) {
                Element element = itr.next();
                Axis axis = element.getAxis();
                Integer posForAxis = pos.get(axis);
                if (posForAxis == null || posForAxis < posMax) {
                    posForAxis = posMax;
                }
                positions.put(element, posForAxis);
                elementsByPosition.put(posForAxis, element);
                int spaceForElement = measurer.getHeightOf(element) + VisualConstants.ELEMENT_MARGIN
                        + VisualConstants.ELEMENT_SQUARE_MARGIN;
                posForAxis += spaceForElement;
                pos.put(axis, posForAxis);
            }
            posMax = Collections.max(pos.values());
        }
        maxPosition = posMax;
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
    public int getPositionOf(Element element) {
        return positions.get(element);
    }

    @Override
    public Collection<Separator> getSeparatorsByPosition(int start, int end) {
        return separators.subMap(start, end).values();
    }

    @Override
    public int getPositionOfSeparator(Separator separator) {
        return separatorPosition.get(separator.getDate());
    }

    @Override
    public Collection<Element> getElementsByPosition(int start, int end) {
        return flatten(elementsByPosition.asMap().subMap(start, end).values());
    }

    private Collection<Element> flatten(Collection<Collection<Element>> values) {
        List<Element> result = new LinkedList<>();
        for (Collection<Element> col : values) {
            result.addAll(col);
        }
        return result;
    }

    @Override
    public int getMaxPosition() {
        return maxPosition;
    }
}
