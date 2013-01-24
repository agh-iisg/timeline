package pl.edu.agh.iisg.timeline.integration;

import java.text.ParseException;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import pl.edu.agh.iisg.timeline.model.TimelineDiagram;
import pl.edu.agh.iisg.timeline.view.TimelineViewer;

public class TimelineView extends ViewPart {

    public static final String TIMELINE_VIEW_ID = "pl.edu.agh.iisg.timeline.view";

    private TimelineViewer timeline;

    @Override
    public void createPartControl(Composite parent) {
        timeline = new TimelineViewer(parent);
        initSampleRealData();
    }

    private void initSampleRealData() {
        try {
            TimelineDiagram diagram = DataGenerator.createRealDataDiagram(3, 100, false);
            timeline.setDiagram(diagram);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFocus() {
        // TODO Auto-generated method stub
    }

    public void setDiagram(TimelineDiagram diagram) {
        timeline.setDiagram(diagram);
    }

}
