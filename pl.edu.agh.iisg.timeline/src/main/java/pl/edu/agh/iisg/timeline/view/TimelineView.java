package pl.edu.agh.iisg.timeline.view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import pl.edu.agh.iisg.timeline.model.TimelineDiagram;

public class TimelineView extends ViewPart {

    public static final String TIMELINE_VIEW_ID = "pl.edu.agh.iisg.timeline.view";

    private TimelineViewer timeline;

    @Override
    public void createPartControl(Composite parent) {
        timeline = new TimelineViewer(parent);
    }

    @Override
    public void setFocus() {

    }

    public void setDiagram(TimelineDiagram diagram) {
        timeline.setDiagram(diagram);
    }

}
