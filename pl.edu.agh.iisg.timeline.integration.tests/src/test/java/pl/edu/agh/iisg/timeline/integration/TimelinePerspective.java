package pl.edu.agh.iisg.timeline.integration;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class TimelinePerspective implements IPerspectiveFactory {
	public static final String TIMELINE_PERSPECTIVE = "pl.edu.agh.iisg.timeline.perspective"; //$NON-NLS-1$

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
	}

}
