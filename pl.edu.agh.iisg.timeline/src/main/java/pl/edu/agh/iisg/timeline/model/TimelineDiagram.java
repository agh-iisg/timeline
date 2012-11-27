package pl.edu.agh.iisg.timeline.model;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Preconditions;

public class TimelineDiagram {
	private Long minDateTime;
	private Long maxDateTime;
	private Long initialDateTime;

	private List<Axis> axes;

	private TimelineDiagram() {
		axes = new LinkedList<>();
	}

	protected void validate() {
		try {
			Preconditions.checkNotNull(minDateTime);
			Preconditions.checkNotNull(maxDateTime);
		} catch (NullPointerException e) {
			throw new IllegalArgumentException(e);
		}
		if (minDateTime > maxDateTime) {
			throw new IllegalArgumentException("MinDateTime cannot be greated than MaxDateTime");
		}
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

	public boolean removeAxis(Axis axis) {
		Preconditions.checkNotNull(axis);
		return axes.remove(axis);
	}

	public List<Axis> getAxes() {
		return Collections.unmodifiableList(axes);
	}

	public static TimelineDiagramBuilder builder() {
		return new TimelineDiagramBuilder();
	}

	public static class TimelineDiagramBuilder {

		private TimelineDiagram diagram;

		private TimelineDiagramBuilder() {
			diagram = new TimelineDiagram();
		}

		public TimelineDiagramBuilder minDateTime(Date date) {
			diagram.setMinDateTime(date.getTime());
			return this;
		}

		public TimelineDiagramBuilder minDateTime(Long date) {
			diagram.setMinDateTime(date);
			return this;
		}

		public TimelineDiagramBuilder maxDateTime(Date date) {
			diagram.setMaxDateTime(date.getTime());
			return this;
		}

		public TimelineDiagramBuilder maxDateTime(Long date) {
			diagram.setMaxDateTime(date);
			return this;
		}
		public TimelineDiagram build() {
			diagram.validate();
			return diagram;
		}

	}
}
