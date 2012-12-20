package pl.edu.agh.iisg.timeline.editpart;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.AxisElement;
import pl.edu.agh.iisg.timeline.model.Separator;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;
import pl.edu.agh.iisg.timeline.positioner.DiscretePositioner;
import pl.edu.agh.iisg.timeline.positioner.IPositioner;
import pl.edu.agh.iisg.timeline.view.TimelineConstants;

public class TimelineDiagramEditPart extends AbstractGraphicalEditPart {

	private IPositioner positioner = new DiscretePositioner(1000);

	private Map<Axis, IFigure> axisLayers = new HashMap<>();

	public TimelineDiagramEditPart(TimelineDiagram model) {
		super.setModel(model);
		initElementPositions();
	}

	private void initElementPositions() {
		Collection<AxisElement> elements = ((TimelineDiagram) getModel())
				.getAxisElements();
		positioner.position(elements);
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
	protected List<Object> getModelChildren() {
		List<Object> list = new LinkedList<>();
		list.addAll(((TimelineDiagram) getModel()).getAxes());
		list.addAll(((TimelineDiagram) getModel()).getAxisElements());
		list.addAll(positioner.getSeparatorsByPosition(0, 1000000));
		return list;
	}

	@Override
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (childEditPart instanceof AxisEditPart) {
			addAxisChildVisual((AxisEditPart) childEditPart, index);
		} else if (childEditPart instanceof AxisElementEditPart) {
			addAxisElementChildVisual((AxisElementEditPart) childEditPart);
		} else if (childEditPart instanceof SeparatorEditPart) {
			addSeparatorChildVisual((SeparatorEditPart) childEditPart);
		}
	}

	private void addAxisChildVisual(AxisEditPart childEditPart, int index) {
		IFigure axesLayerChild = childEditPart.getFigure();
		((TimelineRootEditPart) getRoot()).getAxesLayer().add(axesLayerChild);

		IFigure axisLayer = createXYFigure();
		((TimelineRootEditPart) getRoot()).getEventsLayer().add(axisLayer);
		axisLayers.put((Axis) childEditPart.getModel(), axisLayer);
	}

	private IFigure createXYFigure() {
		Figure figure = new Figure();
		figure.setLayoutManager(new XYLayout());
		return figure;
	}

	private void addAxisElementChildVisual(AxisElementEditPart childEditPart) {
		AxisElement model = (AxisElement) childEditPart.getModel();
		IFigure parent = axisLayers.get(model.getAxis());
		IFigure child = childEditPart.getFigure();
		parent.add(child);
		int y = getYIndexOf((AxisElement) childEditPart.getModel());
		parent.setConstraint(child, new Rectangle(5, y,
				TimelineConstants.ELEMENT_WIDTH, 80));
	}

	private void addSeparatorChildVisual(SeparatorEditPart childEditPart) {
		Separator model = (Separator) childEditPart.getModel();
		int position = positioner.getPositionOfSeparator(model.getValue());
		Layer layer = ((TimelineRootEditPart) getRoot()).getSeparatorsLayer();
		IFigure figure = childEditPart.getFigure();
		layer.add(figure);
		layer.setConstraint(figure, new Rectangle(new Point(0, position),
				new Dimension(1000, 100)));
	}

	private int getYIndexOf(AxisElement element) {
		return positioner.getPositionOf(element);
	}
}