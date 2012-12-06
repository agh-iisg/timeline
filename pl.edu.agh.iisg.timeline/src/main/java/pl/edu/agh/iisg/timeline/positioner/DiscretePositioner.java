package pl.edu.agh.iisg.timeline.positioner;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import pl.edu.agh.iisg.timeline.model.AxisElement;

public class DiscretePositioner implements IPositioner {

	private static final long GROUP_GAP = 100;
	private static final long ELEMENT_GAP = 200;

	private long start = 0L;

	private long interval;

	public DiscretePositioner(long interval) {
		this.interval = interval;
	}

	@Override
	public SortedMap<Long, AxisElement> position(List<AxisElement> elements) {
		long[] dates = extractDates(elements);
		List<Integer> groups = groupByIntervals(dates);
		return positionInGroups(elements, groups);
	}

	private long[] extractDates(List<AxisElement> elements) {
		long[] res = new long[elements.size()];
		int i = 0;
		for (AxisElement e : elements) {
			res[i++] = e.getDate();
		}
		return res;
	}

	private List<Integer> groupByIntervals(long[] dates) {
		List<Integer> groups = new LinkedList<>();
		long intStart = (dates[0] - start) % interval;
		int group = 0;
		for (long date : dates) {
			if (date < intStart + interval) {
				group++;
			} else {
				groups.add(group);
				group = 1;
				intStart = (date - intStart) % interval;
			}
		}
		groups.add(group);
		return groups;
	}

	private SortedMap<Long, AxisElement> positionInGroups(
			List<AxisElement> elements, List<Integer> groups) {
		SortedMap<Long, AxisElement> res = new TreeMap<>();
		long x = 0;
		Iterator<AxisElement> itr = elements.iterator();
		for (Integer group : groups) {
			x += GROUP_GAP;
			for (int i = 0; i < group; i++) {
				res.put(x, itr.next());
				x += ELEMENT_GAP;
			}
		}
		return res;
	}

	public void setGranulation(long granulation) {
		this.interval = granulation;
	}

	public void setStart(long start) {
		this.start = start;
	}
}
