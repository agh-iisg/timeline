package pl.edu.agh.iisg.timeline.view;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import pl.edu.agh.iisg.timeline.editpart.TimelineRootEditPart;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;

public class TimelineView extends ViewPart {

	private ScrollingGraphicalViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		createDiagram(parent);
	}

	@Override
	public void setFocus() {

	}

	private FigureCanvas createDiagram(Composite parent) {
		TimelineDiagram timelineDiagram = createEmptyDiagram(parent);
		viewer = new ScrollingGraphicalViewer();
		viewer.createControl(parent);
		viewer.setRootEditPart(new TimelineRootEditPart());
		viewer.getControl().setBackground(ColorConstants.white);
		viewer.setEditPartFactory(new TimelineEditPartsFactory());
		viewer.setContents(timelineDiagram);
		return (FigureCanvas) viewer.getControl();
	}

	private TimelineDiagram createEmptyDiagram(Composite parent) {
		TimelineDiagram diagram = TimelineDiagram.builder().name("Timeline").minDateTime(1L)
				.maxDateTime(10L).initialDateTime(5L).build();
		return diagram;
	}


	public void setDiagram(TimelineDiagram diagram) {
		// XXX [kpietak] I'm not sure if it's right way to refresh the view contents
		// but it works now. In the target solution we will use editor so it wouldn't be an issue
		viewer.setRootEditPart(new TimelineRootEditPart());
        viewer.setContents(diagram);
	}
}
