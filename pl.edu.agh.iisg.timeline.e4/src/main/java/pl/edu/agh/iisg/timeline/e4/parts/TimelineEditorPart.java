 
package pl.edu.agh.iisg.timeline.e4.parts;

import javax.annotation.PostConstruct;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.parts.GraphicalViewerImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.widgets.Composite;

import pl.edu.agh.iisg.timeline.common.editpart.TimelineEditPartsFactory;
import pl.edu.agh.iisg.timeline.common.editpart.TimelineRootEditPart;
import pl.edu.agh.iisg.timeline.common.model.TimelineDiagram;

@SuppressWarnings("restriction")
public class TimelineEditorPart {
	
	private EditPartViewer viewer;
	
	private TimelineRootEditPart rootEditPart;
	
	@PostConstruct
	public void createControls(Composite parent) {
		EditPartViewer viewer = new GraphicalViewerImpl();
		viewer.createControl(parent);
		setEditPartViewer(viewer);
		configureEditPartViewer();
	}
	
	@Focus
	public void onFocus() {
		getEditPartViewer().setFocus(rootEditPart);
	}
	
	public void setFocus() {
		getEditPartViewer().setFocus(rootEditPart);
	}
	
	public void setEditPartViewer(EditPartViewer viewer) {
		this.viewer = viewer;
	}
	
	public EditPartViewer getEditPartViewer() {
		return viewer;
	}
	
	public void setDiagram(TimelineDiagram diagram) {
		getEditPartViewer().setContents(diagram);
	}
	
	private void configureEditPartViewer() {
		configureViewerRootEditPart();
		configureViewerEditPartsFactory();
		configureViewerLookAndControls();
	}
	
	private void configureViewerRootEditPart() {
		rootEditPart = new TimelineRootEditPart();
		getEditPartViewer().setRootEditPart(rootEditPart);
	}
	
	private void configureViewerEditPartsFactory() {
        getEditPartViewer().setEditPartFactory(new TimelineEditPartsFactory());
    }

    private void configureViewerLookAndControls() {
        getEditPartViewer().getControl().setBackground(ColorConstants.white);
        addKeyControl();
        addMouseScroll();
    }

    private void addMouseScroll() {
        getEditPartViewer().getControl().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseScrolled(MouseEvent e) {
                rootEditPart.scrollVertical(e.count);
            }
        });
    }

    private void addKeyControl() {
        getEditPartViewer().getControl().addKeyListener(new KeyAdapter() {
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

	
}