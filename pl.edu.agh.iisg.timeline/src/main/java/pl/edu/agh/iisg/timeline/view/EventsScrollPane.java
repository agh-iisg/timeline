package pl.edu.agh.iisg.timeline.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.ScrollPane;

import pl.edu.agh.iisg.timeline.testviewer.EventsRefresh;
import pl.edu.agh.iisg.timeline.testviewer.FakeEventsRefresher;
import pl.edu.agh.iisg.timeline.testviewer.IEventsRefresher;

public class EventsScrollPane extends ScrollPane {

	private final Layer layer;

	private final IEventsRefresher eventsRefresher = new FakeEventsRefresher();

	public EventsScrollPane(Layer eventsLayer) {
		this.layer = eventsLayer;
		init();
	}

	private void init() {
		// addRefreshOnViewportChangeListener();
		this.setContents(layer);
		// refresh(0);
	}

	private void addRefreshOnViewportChangeListener() {
		this.getViewport().getVerticalRangeModel()
				.addPropertyChangeListener(new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						// editPart.refresh();

					}
				});
	}

	private void refresh(int position) {
		EventsRefresh refresh = eventsRefresher.refreshEvents(position);
		if (refresh.shouldRefresh()) {
			//layer.removeFigures(refresh.getEventsToRemove());
			//layer.addFigures(refresh.getEventsToAdd());

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
