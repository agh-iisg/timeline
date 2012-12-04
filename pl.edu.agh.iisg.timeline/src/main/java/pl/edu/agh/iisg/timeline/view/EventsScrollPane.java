package pl.edu.agh.iisg.timeline.view;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.ScrollPane;

public class EventsScrollPane extends ScrollPane {

	private final Layer layer;

	public EventsScrollPane(Layer eventsLayer) {
		this.layer = eventsLayer;

		init();
	}

	private void init() {
		this.setContents(layer);
	}

	@Override
	public void paint(Graphics graphics) {
		int oldAlpha = graphics.getAlpha();

		graphics.setAlpha(128);
		super.paint(graphics);
		graphics.setAlpha(oldAlpha);
	}

}
