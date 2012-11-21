package pl.edu.agh.iisg.timeline.model;

import org.eclipse.jface.resource.ImageDescriptor;

import com.google.common.base.Preconditions;
import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;

public class Axis {

	private String name;

	private ImageDescriptor imageDesc;

	private SortedSetMultimap<Long, AxisElement> elements;

	public Axis(String name, ImageDescriptor imageDesc) {
		this(name);
		this.imageDesc = imageDesc;
	}

	public Axis(String name) {
		this();
		Preconditions.checkNotNull(name);
		this.name = name;
	}

	protected Axis() {
		elements = TreeMultimap.create();
	}


}
