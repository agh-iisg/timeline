package pl.edu.agh.iisg.timeline.testviewer;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class TimelinesLayer extends FreeformLayer {

	private final static int HEADER_ICON_WIDTH = 30;
	private final static int HEADER_ICON_HEIGHT = 30;

	private final static int TIMELINE_LENGTH = TimelineConstantsOld.DEFAULT_TIMELINE_LENGTH;

	private int width = TimelineConstantsOld.DEFAULT_TIMELINE_WIDTH;
	private int headerHeight = TimelineConstantsOld.DEFAULT_TIMELINE_HEADER_HEIGHT;

	private final XYLayout layout = new XYLayout();

	public TimelinesLayer(String[] names) {
		init();

		createHeaders(names);
		createVerticalTimelines(names.length);
	}

	private void init() {
		setLayoutManager(layout);
		setBackgroundColor(ColorConstants.white);
	}

	private void createHeaders(String[] names) {
		int left = 0;
		for (String name : names) {
			createHeaderIcon(left);
			createHeaderLabel(left, name);
			left += width;
		}

	}

	private void createHeaderIcon(int left) {
		int leftMargin = left + (width - HEADER_ICON_WIDTH) / 2;

		RectangleFigure rect = new RectangleFigure();
		rect.setPreferredSize(HEADER_ICON_WIDTH, HEADER_ICON_HEIGHT);
		rect.setLineWidth(2);

		add(rect);
		layout.setConstraint(
				rect,
				new Rectangle(new Point(leftMargin, 0), rect.getPreferredSize()));
	}

	private void createHeaderLabel(int left, String name) {
		Label label = new Label(name);

		add(label);
		layout.setConstraint(label, new Rectangle(new Point(left,
				HEADER_ICON_HEIGHT), new Point(left + width, headerHeight)));

	}

	private void createVerticalTimelines(int n) {
		for (int i = 0; i < n; i++) {
			createVerticalTimeline(i * width);
		}

	}

	private void createVerticalTimeline(int left) {
		int leftMargin = left + (width / 2);

		Polyline line = new Polyline();
		line.addPoint(new Point(leftMargin, headerHeight));
		line.addPoint(new Point(leftMargin, headerHeight + TIMELINE_LENGTH));

		line.setForegroundColor(ColorConstants.green);
		line.setLineWidth(2);

		add(line);

	}
}
