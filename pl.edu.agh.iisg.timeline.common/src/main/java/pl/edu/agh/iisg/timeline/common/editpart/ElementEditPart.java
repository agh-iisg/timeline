package pl.edu.agh.iisg.timeline.common.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.common.model.Element;

import pl.edu.agh.iisg.timeline.common.figure.ElementFigure;



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
