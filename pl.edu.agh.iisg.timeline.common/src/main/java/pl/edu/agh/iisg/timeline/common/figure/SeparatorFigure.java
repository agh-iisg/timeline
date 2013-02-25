package pl.edu.agh.iisg.timeline.common.figure;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;

import pl.edu.agh.iisg.timeline.common.VisualConstants;

public class SeparatorFigure extends RectangleFigure {

    public SeparatorFigure(String label, int axis) {
        init();
        addParts(label, axis);
    }

    private void init() {
        setBackgroundColor(VisualConstants.BACKGROUND);
        setForegroundColor(VisualConstants.BACKGROUND);
        FlowLayout layout = new FlowLayout();
        setLayoutManager(layout);
        layout.setMinorSpacing(0);
        setBorder(new MarginBorder(VisualConstants.SEPARATOR_MARGIN_TOP_BOTTOM, VisualConstants.AXIS_MARGIN,
                VisualConstants.SEPARATOR_MARGIN_TOP_BOTTOM, 0));
    }

    private void addParts(String label, int axis) {
        add(createPart(label));
        for (int i = 1; i < axis; i++) {
            add(createSpace());
            add(createPart());
        }
    }

    private IFigure createPart(String label) {
        IFigure part = createPart();
        part.setLayoutManager(new BorderLayout());
        part.add(createLabel(label), BorderLayout.CENTER);
        return part;
    }

    private IFigure createPart() {
        RectangleFigure rect = new RectangleFigure();
        rect.setBackgroundColor(VisualConstants.SEPARATOR_BACKGROUND);
        rect.setForegroundColor(VisualConstants.SEPARATOR_BACKGROUND);
        rect.setPreferredSize(VisualConstants.AXIS_WIDTH, VisualConstants.SEPARATOR_HEIGHT);
        return rect;
    }

    private IFigure createLabel(String name) {
        Label label = new Label(name);
        label.setForegroundColor(VisualConstants.SEPARATOR_FONT_COLOR);
        label.setBackgroundColor(VisualConstants.SEPARATOR_FONT_COLOR);
        label.setFont(VisualConstants.SEPARATOR_FONT);
        label.setLabelAlignment(PositionConstants.LEFT);
        label.setBorder(new MarginBorder(0, VisualConstants.SEPARATOR_LABEL_MARGIN, 0, 0));
        return label;
    }

    private IFigure createSpace() {
        RectangleFigure rect = new RectangleFigure();
        rect.setBackgroundColor(VisualConstants.SEPARATOR_SPACE_BACKGROUND);
        rect.setForegroundColor(VisualConstants.SEPARATOR_SPACE_BACKGROUND);
        rect.setPreferredSize(VisualConstants.AXIS_MARGIN, VisualConstants.SEPARATOR_HEIGHT);
        return rect;
    }
}
