package pl.edu.agh.iisg.timeline.editpart;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.model.TimelineDiagram;
import pl.edu.agh.iisg.timeline.testviewer.EventsLayer;
import pl.edu.agh.iisg.timeline.testviewer.EventsScrollPane;
import pl.edu.agh.iisg.timeline.testviewer.TimelinesLayer;

public class TimelineDiagramEditPart extends AbstractGraphicalEditPart {

	private String[] companies = new String[] { "Firma krzak", "Firma Jesion",
			"Firma Modrzew" };

	private EventsLayer eventsLayer;

	public TimelineDiagramEditPart(TimelineDiagram model) {
		super.setModel(model);
	}

	@Override
	protected IFigure createFigure() {
		Figure root = new LayeredPane();
		XYLayout layout = new XYLayout();
		root.setLayoutManager(layout);

		Layer timelines = new TimelinesLayer(companies);
		root.add(timelines, "Timelines");
		layout.setConstraint(timelines, root.getBounds());

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
	protected List getModelChildren() {
		return Collections.EMPTY_LIST;
		//
		// return ((TimelineDiagram) getModel()).getAxes();

	}

	@Override
	protected void refreshVisuals() {
		// Rectangle bounds = new Rectangle(100, 100, 100, 100);
		// ((GraphicalEditPart) getParent()).setLayoutConstraint(this,
		// getFigure(), bounds);
		super.refreshVisuals();
	}

	public EventsLayer getEventsLayer() {
		return eventsLayer;
	}

	@Override
	public IFigure getContentPane() {
		// TODO Auto-generated method stub
		return eventsLayer;
	}

}
