package pl.edu.agh.iisg.timeline.view;

import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.ScrollPane;

public class TimelineScrollPane extends ScrollPane {

    private final Layer layer;

    public TimelineScrollPane(Layer eventsLayer) {
        this.layer = eventsLayer;

        init();
    }

    private void init() {
        this.setContents(layer);
    }
}
