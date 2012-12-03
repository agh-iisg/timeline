package pl.edu.agh.iisg.timeline.editpart;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.AxisElement;
import pl.edu.agh.iisg.timeline.testviewer.EventFigure;
import pl.edu.agh.iisg.timeline.view.figure.AxisFigure;

public class AxisEditPart extends AbstractGraphicalEditPart {

	private IFigure axesLayerFigure = null;

	public AxisEditPart(Axis model) {
		this.setModel(model);
	}

	@Override
	protected IFigure createFigure() {
		return new EventFigure("ALALALAL", "descr");
	}

	private IFigure createAxesLayerFigure() {
		Axis axis = (Axis) getModel();
		return new AxisFigure(axis.getName());
	}

	public IFigure getAxexLayerFigure() {
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
		// TODO Auto-generated method stub
		// return ((Axis) getModel()).getAxisElementsInRange(
		return Collections.EMPTY_LIST;
	}

}
