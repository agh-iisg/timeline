package pl.edu.agh.iisg.timeline.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.model.Separator;
import pl.edu.agh.iisg.timeline.util.DateConverter;
import pl.edu.agh.iisg.timeline.view.figure.SeparatorFigure;

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
