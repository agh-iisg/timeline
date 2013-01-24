package pl.edu.agh.iisg.timeline.integration;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class TimelinePerspective implements IPerspectiveFactory {
	public static final String TIMELINE_PERSPECTIVE = "pl.edu.agh.iisg.timeline.perspective";

	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.addStandaloneView(TimelineView.TIMELINE_VIEW_ID, true, IPageLayout.TOP, 0.95f, editorArea);

	}

}
