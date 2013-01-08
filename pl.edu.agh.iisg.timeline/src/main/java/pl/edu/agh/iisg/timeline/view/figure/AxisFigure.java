package pl.edu.agh.iisg.timeline.view.figure;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;

public class AxisFigure extends Figure {

    public static int LENGTH = 4000;

    public static int WIDTH = 200;

    private static int LABEL_HEIGHT = 30;

    public AxisFigure(String name) {
        init();

        addLabel(name);
        addLine();

    }

    private void init() {
        setLayoutManager(new BorderLayout());
        setPreferredSize(WIDTH, LENGTH + LABEL_HEIGHT);
    }

    private void addLabel(String name) {
        Label label = new Label(name);
        add(label);
        setConstraint(label, BorderLayout.TOP);
    }

    private void addLine() {
        Figure figure = new Figure();
        GridLayout layout = new GridLayout(1, false);
        layout.marginWidth = WIDTH / 2;
        figure.setLayoutManager(layout);
        add(figure);
        setConstraint(figure, BorderLayout.CENTER);

        RectangleFigure rect = new RectangleFigure();
        rect.setForegroundColor(ColorConstants.green);
        rect.setBackgroundColor(ColorConstants.green);
        rect.setSize(3, 10);

        figure.add(rect);
        figure.setConstraint(rect, new GridData(GridData.FILL_VERTICAL));
    }

}
