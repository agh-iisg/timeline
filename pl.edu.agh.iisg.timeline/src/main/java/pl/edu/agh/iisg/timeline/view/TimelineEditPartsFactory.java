package pl.edu.agh.iisg.timeline.view;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import pl.edu.agh.iisg.timeline.editpart.TimelineDiagramEditPart;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;

public class TimelineEditPartsFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof TimelineDiagram) {
			return new TimelineDiagramEditPart();
		}
		return null;
	}

}
