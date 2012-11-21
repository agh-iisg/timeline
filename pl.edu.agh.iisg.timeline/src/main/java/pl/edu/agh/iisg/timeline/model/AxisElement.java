package pl.edu.agh.iisg.timeline.model;

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
		setOwner(owner);
		setName(name);
		setDate(date);
		pcph = new PropertyChangeProviderHelper(this);
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

	// END Overridden Methods
}
