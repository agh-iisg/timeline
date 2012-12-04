package pl.edu.agh.iisg.timeline.editpart;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;

public class TimelineDiagramEditPart extends AbstractGraphicalEditPart {

	public TimelineDiagramEditPart(TimelineDiagram model) {
		super.setModel(model);
	}

	@Override
	protected IFigure createFigure() {
		return new Figure();
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
	}

	@Override
	protected List<Axis> getModelChildren() {
		return ((TimelineDiagram) getModel()).getAxes();
	}

	@Override
	protected void addChildVisual(EditPart childEditPart, int index) {
		IFigure axesLayerChild = ((AxisEditPart) childEditPart)
				.getAxesLayerFigure();
		((TimelineRootEditPart) getRoot()).getAxesLayer().add(axesLayerChild,
				index);

		IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
		((TimelineRootEditPart) getRoot()).getEventsLayer().add(child, index);
	}
}
