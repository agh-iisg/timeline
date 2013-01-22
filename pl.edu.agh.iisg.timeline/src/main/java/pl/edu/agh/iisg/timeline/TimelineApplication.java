package pl.edu.agh.iisg.timeline;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import pl.edu.agh.iisg.timeline.util.FontsLoader;

public class TimelineApplication implements IApplication {

	private static Display display;
	private static final String FONTS_DIR = "fonts";

	@Override
	public Object start(IApplicationContext context) throws Exception {
		display = PlatformUI.createDisplay();

		try {
			FontsLoader.loadFromDirectory(FONTS_DIR);
			int returnCode = PlatformUI.createAndRunWorkbench(display,
					new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			}
			return IApplication.EXIT_OK;
		} finally {
			if (!display.isDisposed()) {
				display.dispose();
			}
		}
	}

	@Override
	public void stop() {

	}

}
