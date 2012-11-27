package pl.edu.agh.iisg.timeline.editpart;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.model.TimelineDiagram;

public class TimelineDiagramEditPart extends AbstractGraphicalEditPart {


	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

	@Override
	protected List getModelChildren() {
		return ((TimelineDiagram)getModel()).getAxes();
	}





}
