package pl.edu.agh.iisg.timeline.integration;

import java.text.ParseException;

import org.eclipse.swt.widgets.Composite;

import pl.edu.agh.iisg.timeline.model.TimelineDiagram;

public class TimelineView extends pl.edu.agh.iisg.timeline.view.TimelineView {

    @Override
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);
        initSampleRealData();
    }

    private void initSampleRealData() {
        try {
            TimelineDiagram diagram = DataGenerator.createRealDataDiagram(3, 100, false);
            setDiagram(diagram);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
