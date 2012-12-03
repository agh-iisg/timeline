package pl.edu.agh.iisg.timeline.testviewer;

import org.eclipse.draw2d.geometry.Rectangle;

import pl.edu.agh.iisg.timeline.view.EventFigure;

public class AbsoluteEventFigure {

	private EventFigure figure;
	private Rectangle position;

	public AbsoluteEventFigure(EventFigure figure, Rectangle position) {
		this.figure = figure;
		this.position = position;
	}

	public EventFigure getFigure() {
		return figure;
	}

	public Rectangle getPosition() {
		return position;
	}
}
