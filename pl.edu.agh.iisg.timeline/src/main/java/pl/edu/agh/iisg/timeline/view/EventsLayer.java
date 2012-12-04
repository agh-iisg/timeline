package pl.edu.agh.iisg.timeline.view;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Layer;

public class EventsLayer extends Layer {

	private final FlowLayout layout = new FlowLayout();

	public EventsLayer() {
		init();
	}

	private void init() {
		setLayoutManager(layout);
		setBackgroundColor(ColorConstants.white);
	}

	@Override
	public void paint(Graphics graphics) {
		int oldAlpha = graphics.getAlpha();

		graphics.setAlpha(255);
		super.paint(graphics);
		graphics.setAlpha(oldAlpha);
	}
}
