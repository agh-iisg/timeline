package pl.edu.agh.iisg.timeline.view.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;

public class SeparatorFigure extends Figure {

	public static int LENGTH = 4000;

	public SeparatorFigure(String label) {
		init();
		add(createLine());
		add(createLabel(label));
	}

	private void init() {
		setLayoutManager(new ToolbarLayout());
	}

	private IFigure createLine() {
		RectangleFigure rect = new RectangleFigure();
		rect.setForegroundColor(ColorConstants.orange);
		return rect;
	}

	private IFigure createLabel(String labelName) {
		Label label = new Label(labelName);
		return label;
	}
}
