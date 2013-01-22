package pl.edu.agh.iisg.timeline.view.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

import pl.edu.agh.iisg.timeline.VisualConstants;

public class ElementsForAxisLayer extends Figure {

    private RectangleFigure line;

    private int lineHeight = 0;

    public ElementsForAxisLayer() {
        init();
        addLine();
    }

    private void init() {
        setLayoutManager(new XYLayout());
        addDynamicLineLength();
    }

    private void addDynamicLineLength() {
        addFigureListener(new FigureListener() {

            @Override
            public void figureMoved(IFigure source) {
                if (getSize().height != lineHeight) {
                    lineHeight = getSize().height;
                    setConstraint(line, new Rectangle(0, 0, VisualConstants.VERTICAL_LINE_WIDTH, lineHeight));
                }
            }
        });
    }

    private void addLine() {
        line = new RectangleFigure();
        line.setBackgroundColor(VisualConstants.VERTICAL_LINE_COLOR);
        line.setForegroundColor(VisualConstants.VERTICAL_LINE_COLOR);
        add(line);
    }
}
