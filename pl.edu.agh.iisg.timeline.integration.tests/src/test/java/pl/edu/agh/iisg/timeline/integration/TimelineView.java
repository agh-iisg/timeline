package pl.edu.agh.iisg.timeline.integration;

import java.text.ParseException;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

import pl.edu.agh.iisg.timeline.model.TimelineDiagram;
import pl.edu.agh.iisg.timeline.view.TimelineViewer;

public class TimelineView extends ViewPart {

    public static final String TIMELINE_VIEW_ID = "pl.edu.agh.iisg.timeline.view"; //$NON-NLS-1$

    private static final String OPEN_TIMELINE_EDITOR_ID = "pl.edu.agh.iisg.timeline.example.open.timeline.editor"; //$NON-NLS-1$

    private TimelineViewer timelineViewer;

    @Override
    public void createPartControl(Composite parent) {
        timelineViewer = new TimelineViewer(parent);
        initSampleRealData();
        hookDoubleClickCommand();
    }

    private void initSampleRealData() {
        try {
            TimelineDiagram diagram = DataGenerator.createRealDataDiagram(3, 100, false);
            timelineViewer.setDiagram(diagram);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFocus() {
        // TODO Auto-generated method stub
    }

    public void setDiagram(TimelineDiagram diagram) {
        timelineViewer.setDiagram(diagram);
    }

    private void hookDoubleClickCommand() {
        timelineViewer.getControl().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDoubleClick(MouseEvent e) {
                super.mouseDoubleClick(e);
                IHandlerService handlerService = (IHandlerService)getSite().getService(IHandlerService.class);
                try {
                    handlerService.executeCommand(OPEN_TIMELINE_EDITOR_ID, null);
                } catch (Exception ex) {
                    throw new RuntimeException(String.format("Command with id: %s not found.", OPEN_TIMELINE_EDITOR_ID)); //$NON-NLS-1$
                }
            }

        });
    }

}
