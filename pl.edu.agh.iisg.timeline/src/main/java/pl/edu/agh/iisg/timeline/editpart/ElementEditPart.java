package pl.edu.agh.iisg.timeline.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.model.Element;
import pl.edu.agh.iisg.timeline.view.figure.ElementFigure;

public class ElementEditPart extends AbstractGraphicalEditPart {

    public ElementEditPart(Element model) {
        setModel(model);
    }

    @Override
    protected IFigure createFigure() {
        Element model = (Element)getModel();
        return new ElementFigure(model.getTitle(), model.getDescription());
    }

    @Override
    protected void createEditPolicies() {
        // TODO Auto-generated method stub
    }

}
