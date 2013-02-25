package pl.edu.agh.iisg.timeline.common.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.common.util.DateConverter;

import pl.edu.agh.iisg.timeline.common.model.Separator;

import pl.edu.agh.iisg.timeline.common.figure.SeparatorFigure;



public class SeparatorEditPart extends AbstractGraphicalEditPart {

    public SeparatorEditPart(Separator model) {
        this.setModel(model);
    }

    @Override
    protected IFigure createFigure() {
        Separator separator = (Separator)getModel();
        DateConverter converter = ((TimelineDiagramEditPart)getParent()).getConverter();
        return new SeparatorFigure(converter.asString(separator.getDate()), separator.getAxis());
    }

    @Override
    protected void createEditPolicies() {

    }
}
