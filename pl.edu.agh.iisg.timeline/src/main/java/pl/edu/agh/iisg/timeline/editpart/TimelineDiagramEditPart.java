package pl.edu.agh.iisg.timeline.editpart;

import java.util.List;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;

public class TimelineDiagramEditPart extends AbstractGraphicalEditPart {

	public TimelineDiagramEditPart(TimelineDiagram model) {
		super.setModel(model);
	}

	@Override
	protected IFigure createFigure() {
		ScrollPane figure = new ScrollPane();
		Layer layer = new Layer();
		layer.setLayoutManager(new FlowLayout());
		figure.setContents(layer);
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

	@Override
	protected List<Axis> getModelChildren() {
		return ((TimelineDiagram) getModel()).getAxes();

	}

	public Layer getAxesLayer() {
		return ((TimelineRootEditPart) getParent()).getAxesLayer();
	}

}
