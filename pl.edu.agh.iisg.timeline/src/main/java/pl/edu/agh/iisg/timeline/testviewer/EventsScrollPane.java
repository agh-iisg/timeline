package pl.edu.agh.iisg.timeline.testviewer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ScrollPane;

public class EventsScrollPane extends ScrollPane {

	private final EventsLayer layer;

	private final IEventsRefresher eventsRefresher = new FakeEventsRefresher();

	public EventsScrollPane(EventsLayer eventsLayer) {
		this.layer = eventsLayer;
		init();
	}

	private void init() {
		addRefreshOnViewportChangeListener();
		this.setContents(layer);
		refresh(0);
	}

	private void addRefreshOnViewportChangeListener() {
		this.getViewport().getVerticalRangeModel()
				.addPropertyChangeListener(new RefreshEventsListener());
	}

	private void refresh(int position) {
		EventsRefresh refresh = eventsRefresher.refreshEvents(position);
		if (refresh.shouldRefresh()) {
			layer.removeFigures(refresh.getEventsToRemove());
			layer.addFigures(refresh.getEventsToAdd());

		}
	}

	@Override
	public void paint(Graphics graphics) {
		int oldAlpha = graphics.getAlpha();

		graphics.setAlpha(128);
		super.paint(graphics);
		graphics.setAlpha(oldAlpha);
	}

	private class RefreshEventsListener implements PropertyChangeListener {

		private static final String ACCEPTED_PROPERTY_NAME = "value";

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (isPositionChangeEvent(evt)) {
				refresh((Integer) evt.getNewValue());
			}

		}

		private boolean isPositionChangeEvent(PropertyChangeEvent evt) {
			return evt.getPropertyName().equals(ACCEPTED_PROPERTY_NAME);
		}

	}

}
