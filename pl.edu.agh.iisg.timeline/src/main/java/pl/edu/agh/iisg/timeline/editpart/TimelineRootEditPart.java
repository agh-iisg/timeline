package pl.edu.agh.iisg.timeline.editpart;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.editparts.SimpleRootEditPart;

import pl.edu.agh.iisg.timeline.testviewer.EventsLayer;
import pl.edu.agh.iisg.timeline.testviewer.EventsScrollPane;

public class TimelineRootEditPart extends SimpleRootEditPart {

	private EventsLayer eventsLayer;

	private Layer axesLayer;

	@Override
	protected IFigure createFigure() {

		Figure root = new LayeredPane();
		XYLayout layout = new XYLayout();
		root.setLayoutManager(layout);

		axesLayer = new Layer();
		axesLayer.setLayoutManager(new FlowLayout());
		root.add(axesLayer);
		layout.setConstraint(axesLayer, root.getBounds());

		eventsLayer = new EventsLayer();
		ScrollPane scroll = new EventsScrollPane(eventsLayer);
		root.add(scroll, "Events");
		layout.setConstraint(scroll, root.getBounds());

		return root;
	}

	@Override
	public IFigure getContentPane() {
		return eventsLayer;
	}

	public Layer getAxesLayer() {
		return axesLayer;
	}

}