package pl.edu.agh.iisg.timeline.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.model.AxisElement;
import pl.edu.agh.iisg.timeline.view.EventFigure;

public class AxisElementEditPart extends AbstractGraphicalEditPart {

	public AxisElementEditPart(AxisElement model) {
		setModel(model);
	}

	@Override
	protected IFigure createFigure() {
		AxisElement model = (AxisElement) getModel();
		return new EventFigure(model.getName(), model.getDescription());
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
	}

}
