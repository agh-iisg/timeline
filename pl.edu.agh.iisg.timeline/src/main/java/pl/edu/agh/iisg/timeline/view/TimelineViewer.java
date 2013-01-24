package pl.edu.agh.iisg.timeline.view;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.ui.parts.GraphicalViewerImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.widgets.Composite;

import pl.edu.agh.iisg.timeline.editpart.TimelineRootEditPart;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;

public class TimelineViewer extends GraphicalViewerImpl {

    private TimelineRootEditPart rootEditPart;

    public TimelineViewer(Composite parent) {
        super();
        createDiagram(parent);
    }

    private void createDiagram(Composite parent) {
        initViewer(parent);
        addMouseScroll();
        addKeyControl();

    }

    private void initViewer(Composite parent) {
        TimelineDiagram timelineDiagram = createEmptyDiagram(parent);
        createControl(parent);
        rootEditPart = new TimelineRootEditPart();
        setRootEditPart(rootEditPart);
        getControl().setBackground(ColorConstants.white);
        setEditPartFactory(new TimelineEditPartsFactory());
        setContents(timelineDiagram);
    }

    private void addMouseScroll() {
        getControl().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseScrolled(MouseEvent e) {
                rootEditPart.scrollVertical(e.count);
            }
        });
    }

    private void addKeyControl() {
        getControl().addKeyListener(new KeyAdapter() {
            private static final int ARROW_STEP = 1;

            private static final int PAGE_STEP = 25;

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.ARROW_DOWN) {
                    rootEditPart.scrollVertical(-ARROW_STEP);
                } else if (e.keyCode == SWT.ARROW_UP) {
                    rootEditPart.scrollVertical(ARROW_STEP);
                } else if (e.keyCode == SWT.ARROW_RIGHT) {
                    rootEditPart.scrollHorizontal(-ARROW_STEP);
                } else if (e.keyCode == SWT.ARROW_LEFT) {
                    rootEditPart.scrollHorizontal(ARROW_STEP);
                } else if (e.keyCode == SWT.PAGE_DOWN) {
                    rootEditPart.scrollVertical(-PAGE_STEP);
                } else if (e.keyCode == SWT.PAGE_UP) {
                    rootEditPart.scrollVertical(PAGE_STEP);
                } else if (e.keyCode == SWT.HOME) {
                    rootEditPart.scrollVerticalToStart();
                } else if (e.keyCode == SWT.END) {
                    rootEditPart.scrollVerticalToEnd();
                }
            }
        });
    }

    private TimelineDiagram createEmptyDiagram(Composite parent) {
        TimelineDiagram diagram = TimelineDiagram.builder().name("Timeline").minDateTime(1L).maxDateTime(10L).initialDateTime(5L).build();
        return diagram;
    }

    public void setDiagram(TimelineDiagram diagram) {
        // XXX [kpietak] I'm not sure if it's right way to refresh the view contents
        // but it works now. In the target solution we will use editor so it wouldn't be an issue
        rootEditPart = new TimelineRootEditPart();
        setRootEditPart(rootEditPart);
        setContents(diagram);
    }

}
