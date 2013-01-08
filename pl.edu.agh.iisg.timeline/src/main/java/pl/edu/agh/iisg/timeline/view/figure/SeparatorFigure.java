package pl.edu.agh.iisg.timeline.view.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;

public class SeparatorFigure extends Figure {

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
        rect.setBackgroundColor(ColorConstants.orange);
        rect.setPreferredSize(10, 5);
        return rect;
    }

    private IFigure createLabel(String labelName) {
        Label label = new Label(labelName);
        label.setLabelAlignment(PositionConstants.LEFT);
        label.setForegroundColor(ColorConstants.black);
        return label;
    }
}
