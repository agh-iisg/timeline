package pl.edu.agh.iisg.timeline.editpart;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.AxisElement;
import pl.edu.agh.iisg.timeline.view.figure.AxisFigure;

public class AxisEditPart extends AbstractGraphicalEditPart {

	public AxisEditPart(Axis model) {
		this.setModel(model);
	}

	@Override
	protected IFigure createFigure() {
		Axis axis = (Axis) getModel();
		return new AxisFigure(axis.getName());
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

	@Override
	protected List<AxisElement> getModelChildren() {
		// TODO Auto-generated method stub
		//return ((Axis) getModel()).getAxisElementsInRange(
		return Collections.EMPTY_LIST;
	}

	@Override
	protected void refreshVisuals() {
		// EventsLayer layer = ((TimelineDiagramEditPart) getParent())
		// .getEventsLayer();
		// layer.add(this.getFigure());
		// Rectangle bounds = new Rectangle(0, 0, AxisFigure.WIDTH,
		// AxisFigure.LENGTH);
		// layer.setConstraint(getFigure(), bounds);
		// super.refreshVisuals();
		// ((GraphicalEditPart) getParent()).setLayoutConstraint(this,
		// getFigure(), bounds);
		TimelineDiagramEditPart timeline = (TimelineDiagramEditPart) getParent();
		timeline.getAxesLayer().add(getFigure());
		// timeline.getAxesLayer().setConstraint(getFigure(), bounds);
		// super.refreshVisuals();
	}

}
