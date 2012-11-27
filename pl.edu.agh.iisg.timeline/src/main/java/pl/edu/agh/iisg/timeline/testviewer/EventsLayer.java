package pl.edu.agh.iisg.timeline.testviewer;

import java.util.Collection;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class EventsLayer extends Layer {

	private final XYLayout layout = new XYLayout();

	public EventsLayer() {
		init();
	}

	private void init() {
		setLayoutManager(layout);
		setBackgroundColor(ColorConstants.white);
	}

	public void removeFigures(Collection<AbsoluteEventFigure> absoluteFigures) {
		for (AbsoluteEventFigure eventToRemove : absoluteFigures) {
			this.remove(eventToRemove.getFigure());
		}
	}

	public void addFigures(Collection<AbsoluteEventFigure> absoluteFigures) {
		for (AbsoluteEventFigure eventToAdd : absoluteFigures) {
			IFigure figure = eventToAdd.getFigure();
			Rectangle position = eventToAdd.getPosition();
			add(figure);
			layout.setConstraint(figure, position);
		}
	}

	@Override
	public void paint(Graphics graphics) {
		int oldAlpha = graphics.getAlpha();

		graphics.setAlpha(255);
		super.paint(graphics);
		graphics.setAlpha(oldAlpha);
	}

}
