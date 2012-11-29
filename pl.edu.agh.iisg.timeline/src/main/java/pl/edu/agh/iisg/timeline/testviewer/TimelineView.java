package pl.edu.agh.iisg.timeline.testviewer;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TimelineView {

	private static final int MAX_WIDTH = 1000;

	private String[] companies = new String[] { "Firma krzak", "Firma Jesion",
			"Firma Modrzew" };

	public static void main(String[] args) {
		new TimelineView().run();
	}

	private void run() {
		Shell shell = new Shell(new Display());
		shell.setSize(1200, 800);
		shell.setText("Timeline");
		shell.setLayout(new GridLayout());
		Canvas canvas = createDiagram(shell);
		canvas.setLayoutData(new GridData(GridData.FILL_BOTH));
		Display display = shell.getDisplay();
		shell.open();
		while (!shell.isDisposed()) {
			while (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private Canvas createDiagram(Composite parent) {
		Figure root = new FreeformLayeredPane();
		root.setFont(parent.getFont());
		XYLayout layout = new XYLayout();
		root.setLayoutManager(layout);
		Canvas canvas = new Canvas(parent, SWT.DOUBLE_BUFFERED);
		canvas.setBackground(ColorConstants.white);
		LightweightSystem lws = new LightweightSystem(canvas);
		lws.setContents(root);

		Layer timelines = new TimelinesLayer(companies);
		root.add(timelines, "Timelines");
		layout.setConstraint(timelines, root.getBounds());

		ScrollPane scroll = new EventsScrollPane(new EventsLayer(), null);
		root.add(scroll, "Events");
		layout.setConstraint(scroll, new Rectangle(new Point(0,
				TimelineConstants.DEFAULT_TIMELINE_HEADER_HEIGHT), new Point(
				MAX_WIDTH, TimelineConstants.DEFAULT_TIMELINE_LENGTH)));

		return canvas;
	}
}
