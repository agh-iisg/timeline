package pl.edu.agh.iisg.timeline.view.figure;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;

public class AxisFigure extends RectangleFigure {

    public static int LENGTH = 4000;

    public static int WIDTH = 200;

    public AxisFigure(String name) {
        init();
        addLabel(name);
    }

    private void init() {
        setLayoutManager(new FlowLayout());
        setPreferredSize(FigureConstants.AXIS_WIDTH, FigureConstants.AXIS_HEIGHT);

        setBackgroundColor(FigureConstants.AXIS_BACKGROUND);
        setForegroundColor(FigureConstants.AXIS_BACKGROUND);
    }

    private void addLabel(String name) {

        // TODO the label should be multiline
        Label label = new Label(name);

        label.setForegroundColor(FigureConstants.AXIS_FONT_COLOR);
        label.setFont(FigureConstants.AXIS_FONT);

        int margin = FigureConstants.AXIS_LABEL_MARGIN;
        label.setBorder(new MarginBorder(margin, margin, margin, margin));

        add(label);
    }
}
