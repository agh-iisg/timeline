package pl.edu.agh.iisg.timeline.view.figure;


import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.graphics.Image;

import pl.edu.agh.iisg.timeline.VisualConstants;

public class AxisFigure extends RectangleFigure {

	public static int LENGTH = 4000;

	public static int WIDTH = 200;

	public AxisFigure(String name) {
		this(name, null);
	}

	public AxisFigure(String name, Image icon) {
		init();
		if (icon != null) {
			addIcon(icon);
		}
		addLabel(name);
	}

	private void init() {
		FlowLayout layout = new FlowLayout();
		layout.setMinorSpacing(0);
		setLayoutManager(layout);
		setPreferredSize(new Dimension(VisualConstants.AXIS_WIDTH,
				VisualConstants.AXIS_HEIGHT));

		setBackgroundColor(VisualConstants.AXIS_BACKGROUND);
		setForegroundColor(VisualConstants.AXIS_BACKGROUND);
	}

	private void addLabel(String name) {
		AxisFlowPage flowPage = new AxisFlowPage();
		TextFlow label = new TextFlow(name);

		label.setForegroundColor(VisualConstants.AXIS_FONT_COLOR);
		label.setFont(VisualConstants.AXIS_FONT);

		int margin = VisualConstants.AXIS_LABEL_MARGIN;

		flowPage.setBorder(new MarginBorder(margin - 3, margin, margin, margin));

		flowPage.add(label);
		add(flowPage);
	}

	private void addIcon(Image icon) {
		ImageFigure iconFigure = new ImageFigure(icon);

		iconFigure.setPreferredSize(VisualConstants.AXIS_ICON_WIDTH, VisualConstants.AXIS_ICON_HEIGHT);
		int margin = VisualConstants.AXIS_LABEL_MARGIN;
		iconFigure.setBorder(new MarginBorder(margin, margin, 0, 0));

		add(iconFigure);
	}
}
