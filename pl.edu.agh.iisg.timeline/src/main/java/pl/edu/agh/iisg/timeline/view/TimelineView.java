package pl.edu.agh.iisg.timeline.view;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import pl.edu.agh.iisg.timeline.editpart.TimelineRootEditPart;
import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.AxisElement;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;

public class TimelineView extends ViewPart {

	private ScrollingGraphicalViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		createDiagram(parent);
		viewer.setContents(createSampleDiagram(parent));
	}

	@Override
	public void setFocus() {

	}

	private FigureCanvas createDiagram(Composite parent) {
		viewer = new ScrollingGraphicalViewer();
		viewer.createControl(parent);
		viewer.setRootEditPart(new TimelineRootEditPart());
		viewer.getControl().setBackground(ColorConstants.white);
		viewer.setEditPartFactory(new TimelineEditPartsFactory());
		return (FigureCanvas) viewer.getControl();
	}

	private TimelineDiagram createSampleDiagram(Composite parent) {
		TimelineDiagram diagram = TimelineDiagram.builder().minDateTime(1L)
				.maxDateTime(10L).initialDate(5L).build();
		Axis axis = new Axis("Firma krewniak");
		diagram.addAxisElement(AxisElement.builder().name("name 1")
				.description("description 1").owner(axis).date(0L).build());
		diagram.addAxis(axis);
		Axis axis2 = new Axis("Firma klusek");
		diagram.addAxis(axis2);
		axis = new Axis("Firma zombie");
		diagram.addAxisElement(AxisElement.builder().name("name 3")
				.description("description 3").owner(axis).date(5L).build());
		diagram.addAxisElement(AxisElement.builder().name("name 2")
				.description("description 2").owner(axis2).date(2000L).build());
		diagram.addAxisElement(AxisElement.builder().name("name 2")
				.description("description 2").owner(axis2).date(4000L).build());
		diagram.addAxisElement(AxisElement.builder().name("name 22")
				.description("description 122").owner(axis2).date(4000L)
				.build());
		diagram.addAxisElement(AxisElement.builder().name("name 2")
				.description("description 2").owner(axis2).date(8000L).build());
		diagram.addAxisElement(AxisElement.builder().name("name 2")
				.description("description 2").owner(axis2).date(10000L).build());
		diagram.addAxis(axis);

		return diagram;
	}

}
