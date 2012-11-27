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

	public AxisElement(Axis owner, String name, String description, Date date,
			Long duration) {
		this(owner, name, date);
		this.description = description;
		this.duration = duration;
	}

	public AxisElement(Axis owner, String name, Date date) {
		this();
		setOwner(owner);
		setName(name);
		setDate(date);

	}

	private AxisElement() {
		pcph = new PropertyChangeProviderHelper(this);
	}

	protected void validate() {
		Preconditions.checkNotNull(pcph);
		Preconditions.checkNotNull(owner);
		Preconditions.checkNotNull(name);
		Preconditions.checkNotNull(date);
		if (duration != null) {
			Preconditions.checkArgument(duration >= 0);
		}

	}

	// END Constructors

	// BEGIN Getters & Setters

	public Axis getOwner() {
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
	public int compareTo(AxisElement o) {
		Preconditions.checkNotNull(o);
		return date.compareTo(o.date);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcph.addPropertyChangeListener(l);

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener l) {
		pcph.removePropertyChangeListener(l);

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
		private AxisElement axisElement;

		private AxisElementBuilder() {
			axisElement = new AxisElement();
		}

		public AxisElementBuilder owner(Axis owner) {
			axisElement.setOwner(owner);
			return this;
		}

		public AxisElementBuilder name(String name) {
			axisElement.setName(name);
			return this;
		}

		public AxisElementBuilder date(Long date) {
			axisElement.setDate(date);
			return this;
		}

		public AxisElementBuilder date(Date date) {
			axisElement.setDate(date);
			return this;
		}

		public AxisElementBuilder description(String desc) {
			axisElement.setDescription(desc);
			return this;
		}

		public AxisElementBuilder setDuration(Long duration) {
			axisElement.setDuration(duration);
			return this;
		}

		public AxisElement build() {
			axisElement.validate();
			return axisElement;
		}
	}

	// END Builder Methods
}
