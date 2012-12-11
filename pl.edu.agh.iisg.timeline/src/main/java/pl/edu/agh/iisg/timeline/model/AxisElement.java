package pl.edu.agh.iisg.timeline.model;

import static com.google.common.base.Objects.equal;
import static java.util.Objects.hash;

import java.beans.PropertyChangeListener;
import java.util.Date;

import pl.edu.agh.iisg.common.property.IPropertyChangeProvider;
import pl.edu.agh.iisg.common.property.PropertyChangeProviderHelper;

import com.google.common.base.Preconditions;

public class AxisElement implements Comparable<AxisElement>,
		IPropertyChangeProvider {

	private static final String OWNER = "AxisElement.OWNER";

	private static final String NAME = "AxisElement.NAME";

	private static final String DESCRIPTION = "AxisElement.DESCRIPTION";

	private static final String DATE = "AxisElement.DATE";

	private static final String DURATION = "AxisElement.DURATION";

	private Axis owner;

	private String name;

	private String description;

	private Long date;

	private Long duration;

	private PropertyChangeProviderHelper pcph;

	// BEGIN Constructors

	public AxisElement(Axis owner, String name, String description, Long date,
			Long duration) {
		this.owner = owner;
		this.name = name;
		this.description = description;
		this.date = date;
		this.duration = duration;

		init();
	}

	private void init() {
		pcph = new PropertyChangeProviderHelper(this);
	}

	// END Constructors

	// BEGIN Getters & Setters

	public Axis getAxis() {
		return owner;
	}

	public void setOwner(Axis owner) {
		Preconditions.checkNotNull(owner);
		Axis oldValue = this.owner;
		this.owner = owner;
		pcph.firePropertyChange(OWNER, oldValue, owner);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		Preconditions.checkNotNull(name);
		String oldValue = this.name;
		this.name = name;
		pcph.firePropertyChange(NAME, oldValue, name);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		String oldValue = this.description;
		this.description = description;
		pcph.firePropertyChange(DESCRIPTION, oldValue, description);

	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		Preconditions.checkNotNull(date);
		Long oldValue = this.date;
		this.date = date;
		pcph.firePropertyChange(DATE, oldValue, date);
	}

	public void setDate(Date date) {
		Preconditions.checkNotNull(date);
		Long oldValue = this.date;
		this.date = date.getTime();
		pcph.firePropertyChange(DATE, oldValue, date);
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		Long oldValue = this.duration;
		this.duration = duration;
		pcph.firePropertyChange(DURATION, oldValue, duration);
	}

	// END Getters & Setters

	// BEGIN Overridden Methods

	@Override
	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcph.addPropertyChangeListener(l);

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener l) {
		pcph.removePropertyChangeListener(l);

	}

	@Override
	public int compareTo(AxisElement o) {
		Preconditions.checkNotNull(o);
		int res = date.compareTo(o.date);
		if (res != 0) {
			return res;
		}
		res = name.compareTo(o.name);
		if (res != 0) {
			return res;
		}
		res = description.compareTo(o.description);
		if (res != 0) {
			return res;
		}
		res = duration.compareTo(o.duration);
		if (res != 0) {
			return res;
		}
		return owner.hashCode() - o.owner.hashCode();
	}

	@Override
	public boolean equals(Object arg) {
		if (arg instanceof AxisElement) {
			final AxisElement that = (AxisElement) arg;
			return equal(owner, that.owner) && equal(name, that.name)
					&& equal(description, that.description)
					&& equal(date, that.date) && equal(duration, that.duration);
		} else {
			return false;
		}

	}

	@Override
	public int hashCode() {
		return hash(owner, name, description, date, duration);
	}

	// END Overridden Methods

	// BEGIN Builder Methods
	public static AxisElementBuilder builder() {
		return new AxisElementBuilder();
	}

	public static class AxisElementBuilder {

		private Axis owner = null;

		private String name = null;

		private String description = null;

		private Long date = null;

		private Long duration = 0L;

		public AxisElementBuilder owner(Axis owner) {
			this.owner = owner;
			return this;
		}

		public AxisElementBuilder name(String name) {
			this.name = name;
			return this;
		}

		public AxisElementBuilder date(Long date) {
			this.date = date;
			return this;
		}

		public AxisElementBuilder date(Date date) {
			this.date = date.getTime();
			return this;
		}

		public AxisElementBuilder description(String desc) {
			this.description = desc;
			return this;
		}

		public AxisElementBuilder setDuration(Long duration) {
			this.duration = duration;
			return this;
		}

		public AxisElement build() {
			validate();
			return new AxisElement(owner, name, description, date, duration);
		}

		protected void validate() {
			Preconditions.checkNotNull(owner);
			Preconditions.checkNotNull(name);
			Preconditions.checkNotNull(date);
			if (duration != null) {
				Preconditions.checkArgument(duration >= 0);
			}
		}
	}

	// END Builder Methods
}
