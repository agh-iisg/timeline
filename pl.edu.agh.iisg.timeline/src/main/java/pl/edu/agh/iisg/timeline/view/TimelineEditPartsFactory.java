package pl.edu.agh.iisg.timeline.view;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import pl.edu.agh.iisg.timeline.editpart.AxisEditPart;
import pl.edu.agh.iisg.timeline.editpart.AxisElementEditPart;
import pl.edu.agh.iisg.timeline.editpart.SeparatorEditPart;
import pl.edu.agh.iisg.timeline.editpart.TimelineDiagramEditPart;
import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.AxisElement;
import pl.edu.agh.iisg.timeline.model.Separator;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;

public class TimelineEditPartsFactory implements EditPartFactory {

    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof TimelineDiagram) {
            return new TimelineDiagramEditPart((TimelineDiagram)model);
        } else if (model instanceof Axis) {
            return new AxisEditPart((Axis)model);
        } else if (model instanceof AxisElement) {
            return new AxisElementEditPart((AxisElement)model);
        } else if (model instanceof Separator) {
            return new SeparatorEditPart((Separator)model);
        }
        return null;
    }

}
