package pl.edu.agh.iisg.timeline.model;

import org.eclipse.jface.resource.ImageDescriptor;

import com.google.common.base.Preconditions;

public class Axis {

	private String name;

	private ImageDescriptor imageDesc;

	public Axis(String name, ImageDescriptor imageDesc) {
		this(name);
		setImageDesc(imageDesc);
	}

	public Axis(String name) {
		this();
		setName(name);
	}

	protected Axis() {

	}

	protected void validate() {
		Preconditions.checkNotNull(name);
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

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Axis axis;

		private Builder() {
			axis = new Axis();
		}

		public Builder name(String name) {
			axis.setName(name);
			return this;
		}

		public Builder imageDesc(ImageDescriptor imageDesc) {
			axis.setImageDesc(imageDesc);
			return this;
		}

		public Axis build() {
			axis.validate();
			return axis;
		}
	}

}
