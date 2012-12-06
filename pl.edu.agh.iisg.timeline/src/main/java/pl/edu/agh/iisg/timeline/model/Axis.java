package pl.edu.agh.iisg.timeline.model;

import java.util.Collection;
import java.util.SortedMap;

import org.eclipse.jface.resource.ImageDescriptor;

import com.google.common.base.Preconditions;
import com.google.common.collect.TreeMultimap;

public class Axis {

	private String name;

	private ImageDescriptor imageDesc;

	private TreeMultimap<Long, AxisElement> elements;

	public Axis(String name, ImageDescriptor imageDesc) {
		this(name);
		setImageDesc(imageDesc);
	}

	public Axis(String name) {
		this();
		setName(name);
	}

	protected Axis() {
		elements = TreeMultimap.create();
	}

	protected void validate() {
		Preconditions.checkNotNull(name);
		Preconditions.checkNotNull(elements);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		Preconditions.checkNotNull(name);
		this.name = name;
	}

	public ImageDescriptor getImageDesc() {
		return imageDesc;
	}

	public void setImageDesc(ImageDescriptor imageDesc) {
		this.imageDesc = imageDesc;
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

	public static AxisBuilder builder() {
		return new AxisBuilder();
	}

	public static class AxisBuilder {
		private Axis axis;

		private AxisBuilder() {
			axis = new Axis();
		}

		public AxisBuilder name(String name) {
			axis.setName(name);
			return this;
		}

		public AxisBuilder imageDesc(ImageDescriptor imageDesc) {
			axis.setImageDesc(imageDesc);
			return this;
		}

		public Axis build() {
			axis.validate();
			return axis;
		}
	}

}
