package pl.edu.agh.iisg.timeline.editpart;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.AxisElement;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;
import pl.edu.agh.iisg.timeline.positioner.DiscretePositioner;
import pl.edu.agh.iisg.timeline.positioner.IPositioner;

import com.google.common.collect.ImmutableBiMap;

public class TimelineDiagramEditPart extends AbstractGraphicalEditPart {

	private IPositioner positioner = new DiscretePositioner(1000);

	private ImmutableBiMap<Integer, AxisElement> positions;

	public TimelineDiagramEditPart(TimelineDiagram model) {
		super.setModel(model);
		initElementPositions();
	}

	private void initElementPositions() {
		List<Axis> axes = ((TimelineDiagram) getModel()).getAxes();
		List<AxisElement> elements = new LinkedList<>();
		for (Axis axis : axes) {
			elements.addAll(axis.getAxisElements());
		}
		Collections.sort(elements);
		positions = ImmutableBiMap.copyOf(positioner.position(elements));
	}

	@Override
	protected IFigure createFigure() {
		return new Figure();
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
	}

	@Override
	protected List<Axis> getModelChildren() {
		return ((TimelineDiagram) getModel()).getAxes();
	}

	@Override
	protected void addChildVisual(EditPart childEditPart, int index) {
		IFigure axesLayerChild = ((AxisEditPart) childEditPart)
				.getAxesLayerFigure();
		((TimelineRootEditPart) getRoot()).getAxesLayer().add(axesLayerChild,
				index);

		IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
		((TimelineRootEditPart) getRoot()).getEventsLayer().add(child, index);
	}

	public ImmutableBiMap<Integer, AxisElement> getPositions() {
		return positions;
	}
}
