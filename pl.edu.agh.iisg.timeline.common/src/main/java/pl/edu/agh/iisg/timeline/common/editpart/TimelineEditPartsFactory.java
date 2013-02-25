package pl.edu.agh.iisg.timeline.common.editpart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import pl.edu.agh.iisg.timeline.common.model.Axis;
import pl.edu.agh.iisg.timeline.common.model.Element;
import pl.edu.agh.iisg.timeline.common.model.Separator;
import pl.edu.agh.iisg.timeline.common.model.TimelineDiagram;


public class TimelineEditPartsFactory implements EditPartFactory {

    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof TimelineDiagram) {
            return new TimelineDiagramEditPart((TimelineDiagram)model);
        } else if (model instanceof Axis) {
            return new AxisEditPart((Axis)model);
        } else if (model instanceof Element) {
            return new ElementEditPart((Element)model);
        } else if (model instanceof Separator) {
            return new SeparatorEditPart((Separator)model);
        }
        return null;
    }

}
