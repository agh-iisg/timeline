package pl.edu.agh.iisg.timeline.positioner;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.AxisElement;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

/**
 * Positioner for the discrete timeline. It positions the elements in the groups
 * representing the same time on the axis.
 *
 * XXX [leszko] if it works too slow, it can be optimised by using only arrays
 * in the logic.
 *
 * @author leszko
 *
 */
public class DiscretePositioner implements IPositioner {

	private static final int SEPARATOR_GAP = 70;
	private static final int ELEMENT_GAP = 120;

	private long start = 0L;

	private long interval;

	private SortedMap<Integer, Long> separators = new TreeMap<>();

	private Map<Long, Integer> separatorPosition = new HashMap<>();

	private Map<AxisElement, Integer> positions = new HashMap<>();

	private Multimap<Integer, AxisElement> elementsByPosition = TreeMultimap
			.create();

	public DiscretePositioner(long interval) {
		this.interval = interval;
	}

	@Override
	public void position(Collection<AxisElement> elements) {
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
		return groups;
	}

	private void positionInGroups(Collection<AxisElement> elements,
			SortedMap<Long, Integer> groups) {
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
				posForAxis += ELEMENT_GAP;
				pos.put(axis, posForAxis);
			}
			posMax = Collections.max(pos.values());
		}
	}

	private void addSeparator(int position, long date) {
		int sepPos = position + SEPARATOR_GAP / 2;
		separators.put(sepPos, date);
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
	public SortedMap<Integer, Long> getSeparators() {
		return separators;
	}

	@Override
	public int getPositionOfSeparator(Long separator) {
		return separatorPosition.get(separator);
	}

}