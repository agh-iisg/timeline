package pl.edu.agh.iisg.timeline.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.view.figure.AxisFigure;

public class AxisEditPart extends AbstractGraphicalEditPart {

    public AxisEditPart(Axis model) {
        this.setModel(model);
    }

    @Override
    protected IFigure createFigure() {
        Axis axis = (Axis)getModel();
        if(axis.getImageDesc() != null) {
        	return new AxisFigure(axis.getName(), axis.getImageDesc().createImage());
        }
        return new AxisFigure(axis.getName());
    }

    @Override
    protected void createEditPolicies() {

    }
}
