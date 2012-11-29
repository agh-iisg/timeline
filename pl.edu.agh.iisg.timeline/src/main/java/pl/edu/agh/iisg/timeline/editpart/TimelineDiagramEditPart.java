package pl.edu.agh.iisg.timeline.editpart;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;
import pl.edu.agh.iisg.timeline.testviewer.EventsLayer;
import pl.edu.agh.iisg.timeline.testviewer.EventsScrollPane;

public class TimelineDiagramEditPart extends AbstractGraphicalEditPart {

	private EventsLayer eventsLayer;

	private Layer axesLayer;

	public TimelineDiagramEditPart(TimelineDiagram model) {
		super.setModel(model);
	}

	@Override
	protected IFigure createFigure() {
		Figure root = new LayeredPane();
		XYLayout layout = new XYLayout();
		root.setLayoutManager(layout);

		axesLayer = new Layer();
		axesLayer.setLayoutManager(new XYLayout());
		root.add(axesLayer);
		layout.setConstraint(axesLayer, root.getBounds());

		eventsLayer = new EventsLayer();
		ScrollPane scroll = new EventsScrollPane(eventsLayer, this);
		root.add(scroll, "Events");
		layout.setConstraint(scroll, root.getBounds());

		return root;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

	@Override
	protected List<Axis> getModelChildren() {
		return ((TimelineDiagram) getModel()).getAxes();

	}

	public EventsLayer getEventsLayer() {
		return eventsLayer;
	}

	@Override
	public IFigure getContentPane() {
		// TODO Auto-generated method stub
		return eventsLayer;
	}

	public Layer getAxisLayer() {
		return axesLayer;
	}

}
