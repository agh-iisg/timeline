package pl.edu.agh.iisg.timeline.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;

import com.google.common.base.Preconditions;
import com.google.common.collect.TreeMultimap;

public class TimelineDiagram {
	private String name;

	private Long minDateTime;
	private Long maxDateTime;
	private Long initialDateTime;

	private List<Axis> axes;

	private TreeMultimap<Long, AxisElement> elements;

	private TimelineDiagram() {

		init();
	}

	private void init() {
		axes = new LinkedList<>();
		elements = TreeMultimap.create();
	}

	protected void validate() {
		try {
			Preconditions.checkNotNull(name);
			Preconditions.checkNotNull(minDateTime);
			Preconditions.checkNotNull(maxDateTime);
			Preconditions.checkNotNull(elements);
		} catch (NullPointerException e) {
			throw new IllegalArgumentException(e);
		}
		if (minDateTime > maxDateTime) {
			throw new IllegalArgumentException(
					"MinDateTime cannot be greated than MaxDateTime");
		}
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMinDateTime() {
		return minDateTime;
	}

	public void setMinDateTime(Long minDateTime) {
		this.minDateTime = minDateTime;
	}

	public Long getMaxDateTime() {
		return maxDateTime;
	}

	public void setMaxDateTime(Long maxDateTime) {
		this.maxDateTime = maxDateTime;
	}

	public Long getInitialDateTime() {
		return initialDateTime;
	}

	public void setInitialDateTime(Long initialDateTime) {
		this.initialDateTime = initialDateTime;
	}

	public boolean addAxis(Axis axis) {
		Preconditions.checkNotNull(axis);
		if (!axes.contains(axis)) {
			return axes.add(axis);
		}
		return false;
	}

	public boolean addAxisElement(AxisElement element) {
		Preconditions.checkNotNull(element);
		return elements.put(element.getDate(), element);
	}

	public void removeAxisElement(AxisElement element) {
		elements.remove(element.getDate(), element);
	}

	public SortedMap<Long, Collection<AxisElement>> getAxisElementsInRange(
			Long start, Long end) {
		return elements.asMap().subMap(start, end);
	}

	public Collection<AxisElement> getAxisElements() {
		return elements.values();
	}

	public boolean removeAxis(Axis axis) {
		Preconditions.checkNotNull(axis);
		return axes.remove(axis);
	}

	public List<Axis> getAxes() {
		return Collections.unmodifiableList(axes);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private TimelineDiagram diagram;

		private Builder() {
			diagram = new TimelineDiagram();
		}

		public Builder name(String name) {
			diagram.setName(name);
			return this;
		}

		public Builder minDateTime(Date date) {
			diagram.setMinDateTime(date.getTime());
			return this;
		}

		public Builder minDateTime(Long date) {
			diagram.setMinDateTime(date);
			return this;
		}

		public Builder maxDateTime(Date date) {
			diagram.setMaxDateTime(date.getTime());
			return this;
		}

		public Builder maxDateTime(Long date) {
			diagram.setMaxDateTime(date);
			return this;
		}

		public Builder initialDateTime(Long date) {
			diagram.setInitialDateTime(date);
			return this;
		}

		public Builder addAxis(Axis axis) {
			diagram.addAxis(axis);
			return this;
		}

		public Axis getAxis(int index) {
			return diagram.getAxes().get(index);
		}

		public Builder addElement(AxisElement element) {
			diagram.addAxisElement(element);
			return this;
		}

		public TimelineDiagram build() {
			diagram.validate();
			return diagram;
		}
	}
}