package pl.edu.agh.iisg.timeline.editpart;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.testviewer.EventFigure;

public class AxisEditPart extends AbstractGraphicalEditPart {

	@Override
	protected IFigure createFigure() {
		return new EventFigure("axis", "friend");
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

	@Override
	protected List getModelChildren() {
		// TODO Auto-generated method stub
		return Collections.EMPTY_LIST;
	}

	@Override
	protected void refreshVisuals() {
		// EventsLayer layer = ((TimelineDiagramEditPart) getParent())
		// .getEventsLayer();
		// layer.add(this.getFigure());
		Rectangle bounds = new Rectangle(0, 0, 100, 100);
		// layer.setConstraint(getFigure(), bounds);
		// super.refreshVisuals();
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), bounds);
		super.refreshVisuals();
	}

}
