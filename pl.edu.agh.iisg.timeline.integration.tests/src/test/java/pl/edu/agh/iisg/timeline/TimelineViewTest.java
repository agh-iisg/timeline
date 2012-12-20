package pl.edu.agh.iisg.timeline;

import java.util.Random;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import pl.edu.agh.iisg.timeline.editpart.TimelineRootEditPart;
import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.AxisElement;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;
import pl.edu.agh.iisg.timeline.view.TimelineEditPartsFactory;

public class TimelineViewTest extends ViewPart {

	private ScrollingGraphicalViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		createDiagram(parent);
	}

	@Override
	public void setFocus() {

	}

	private FigureCanvas createDiagram(Composite parent) {
		TimelineDiagram timelineDiagram = createSampleDiagram(parent);
		viewer = new ScrollingGraphicalViewer();
		viewer.createControl(parent);
		viewer.setRootEditPart(new TimelineRootEditPart());
		viewer.getControl().setBackground(ColorConstants.white);
		viewer.setEditPartFactory(new TimelineEditPartsFactory());
		viewer.setContents(timelineDiagram);
		return (FigureCanvas) viewer.getControl();
	}

	private TimelineDiagram createSampleDiagram(Composite parent) {
		long startDate = 1348351200000L;
		long diff = 3000L;
		Random rand = new Random();

		TimelineDiagram diagram = TimelineDiagram.builder().minDateTime(1L)
				.maxDateTime(10L).initialDate(5L).build();
		Axis axis = new Axis("Firma krewniak");
		diagram.addAxis(axis);

		for (int i = 0; i < 10000; i++) {
			long date = startDate + (rand.nextLong() % diff);
			diagram.addAxisElement(AxisElement.builder()
					.name("name " + String.valueOf(i))
					.description("description " + String.valueOf(i))
					.owner(axis).date(date).build());
		}

		return diagram;
	}
}
