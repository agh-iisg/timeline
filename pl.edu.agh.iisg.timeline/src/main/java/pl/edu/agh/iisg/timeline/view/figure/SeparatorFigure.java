package pl.edu.agh.iisg.timeline.view.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;

public class SeparatorFigure extends Figure {

	public static int LENGTH = 4000;

	public SeparatorFigure(String label) {
		init();
		add(createLine());
	}

	private void init() {
		setLayoutManager(new ToolbarLayout());
	}

	private IFigure createLine() {
		RectangleFigure rect = new RectangleFigure();
		rect.setForegroundColor(ColorConstants.orange);
		return rect;
	}
}
