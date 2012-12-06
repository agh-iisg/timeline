package pl.edu.agh.iisg.timeline.editpart;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.AxisElement;
import pl.edu.agh.iisg.timeline.view.TimelineConstants;
import pl.edu.agh.iisg.timeline.view.figure.AxisFigure;

import com.google.common.collect.ImmutableBiMap;

public class AxisEditPart extends AbstractGraphicalEditPart {

	private IFigure axesLayerFigure = null;

	public AxisEditPart(Axis model) {
		this.setModel(model);
	}

	@Override
	protected IFigure createFigure() {
		Figure figure = new Figure();
		figure.setLayoutManager(new XYLayout());
		return figure;
	}

	private IFigure createAxesLayerFigure() {
		Axis axis = (Axis) getModel();
		return new AxisFigure(axis.getName());
	}

	public IFigure getAxesLayerFigure() {
		if (axesLayerFigure == null) {
			axesLayerFigure = createAxesLayerFigure();
		}
		return axesLayerFigure;
	}

	@Override
	protected void createEditPolicies() {

	}

	@Override
	protected List<AxisElement> getModelChildren() {
		return flatten(((Axis) getModel()).getAxisElementsInRange(
				Long.MIN_VALUE, Long.MAX_VALUE).values());
	}

	private List<AxisElement> flatten(Collection<Collection<AxisElement>> values) {
		List<AxisElement> res = new LinkedList<>();
		for (Collection<AxisElement> element : values) {
			res.addAll(element);
		}
		return res;
	}

	@Override
	protected void addChildVisual(EditPart childEditPart, int index) {
		IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
		getContentPane().add(child, index);
		int yIndex = getYIndexOf((AxisElement) childEditPart.getModel());
		getContentPane().setConstraint(child,
				new Rectangle(0, yIndex, TimelineConstants.ELEMENT_WIDTH, 100));
	}

	private int getYIndexOf(AxisElement element) {
		ImmutableBiMap<Integer, AxisElement> positions = ((TimelineDiagramEditPart) getParent())
				.getPositions();
		return positions.inverse().get(element);
	}

}
