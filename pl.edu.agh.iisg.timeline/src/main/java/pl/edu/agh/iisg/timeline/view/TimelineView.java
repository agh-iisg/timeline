package pl.edu.agh.iisg.timeline.view;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import pl.edu.agh.iisg.timeline.editpart.TimelineDiagramEditPart;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;

public class TimelineView extends ViewPart {

	private ScrollingGraphicalViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		createDiagram(parent);
		viewer.setContents(createSampleDiagram());
	}

	@Override
	public void setFocus() {

	}

	private FigureCanvas createDiagram(Composite parent) {
		viewer = new ScrollingGraphicalViewer();
		viewer.createControl(parent);
		viewer.setRootEditPart(new ScalableRootEditPart());
		viewer.getControl().setBackground(ColorConstants.white);
		viewer.setEditPartFactory(new TimelineEditPartsFactory());
		return (FigureCanvas) viewer.getControl();
	}

	private TimelineDiagram createSampleDiagram() {
		TimelineDiagram diagram = TimelineDiagram.builder().minDateTime(1L)
				.maxDateTime(10L).initialDate(5L).build();

		return diagram;
	}

}
