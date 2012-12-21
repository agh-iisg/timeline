package pl.edu.agh.iisg.timeline.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import pl.edu.agh.iisg.timeline.DataGenerator;
import pl.edu.agh.iisg.timeline.view.TimelineView;

public class CreateSampleTimelineDiagramHandler extends AbstractHandler {

	private static final String VIEW_ID = "pl.edu.agh.iisg.timeline.view";
	private static final String AXES_CNT = "pl.edu.agh.iisg.timeline.create.sample.axesCnt";
	private static final String ELEMENTS_CNT = "pl.edu.agh.iisg.timeline.create.sample.elementsCnt";


	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);
		TimelineView view = null;
		try {
			view = openView(window);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		if (view == null) {
			return null;
		}

		int axesCnt = Integer.parseInt(event.getParameter(AXES_CNT));
		long elementsCont = Long.parseLong(event.getParameter(ELEMENTS_CNT));

		try {
			view.setDiagram(DataGenerator.createSampleDiagram(axesCnt, elementsCont));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private final TimelineView openView(
			final IWorkbenchWindow activeWorkbenchWindow)
			throws PartInitException {

		final IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
		if (activePage == null) {
			return null;
		}
		return (TimelineView) activePage.showView(VIEW_ID, null,
				IWorkbenchPage.VIEW_ACTIVATE);

	}
}
