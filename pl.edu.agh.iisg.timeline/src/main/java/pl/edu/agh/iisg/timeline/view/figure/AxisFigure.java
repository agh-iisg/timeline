package pl.edu.agh.iisg.timeline.view.figure;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;

public class AxisFigure extends RectangleFigure {

    public static int LENGTH = 4000;

    public static int WIDTH = 200;

    public AxisFigure(String name) {
        init();
        addLabel(name);
    }

    private void init() {
        setBackgroundColor(FigureConstants.AXIS_BACKGROUND);
        setForegroundColor(FigureConstants.AXIS_FOREGROUND);
        setLayoutManager(new BorderLayout());
        setPreferredSize(FigureConstants.AXIS_WIDTH, FigureConstants.AXIS_HEIGHT);
    }

    private void addLabel(String name) {
        Label label = new Label(name);
        label.setFont(FigureConstants.AXIS_FONT);
        add(label);
        setConstraint(label, BorderLayout.LEFT);
    }
}
