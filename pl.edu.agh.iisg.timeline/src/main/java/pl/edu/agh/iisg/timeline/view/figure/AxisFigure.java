package pl.edu.agh.iisg.timeline.view.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Point;

public class AxisFigure extends Figure {

	public static int LENGTH = 4000;
	public static int WIDTH = 200;

	private static int LABEL_HEIGHT = 30;

	public AxisFigure(String name) {
		init();

		add(createLabel(name));
		add(createLine());

	}

	private void init() {
		setLayoutManager(new ToolbarLayout());
		setPreferredSize(WIDTH, LENGTH + LABEL_HEIGHT);
	}

	private IFigure createLabel(String name) {
		Label label = new Label(name);
		return label;
	}

	private IFigure createLine() {
		Polyline line = new Polyline();
		line.addPoint(new Point(WIDTH / 2, LABEL_HEIGHT));
		line.addPoint(new Point(WIDTH / 2, LENGTH + LABEL_HEIGHT));

		line.setForegroundColor(ColorConstants.green);
		line.setLineWidth(2);

		return line;
	}

}
