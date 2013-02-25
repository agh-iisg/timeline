package pl.edu.agh.iisg.timeline.common.figure;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.MarginBorder;

import pl.edu.agh.iisg.timeline.common.VisualConstants;

public class AxisLayer extends Layer {

    public AxisLayer() {
        FlowLayout layout = new FlowLayout();
        layout.setMinorSpacing(VisualConstants.AXIS_MARGIN);
        setLayoutManager(layout);
        int margin = VisualConstants.AXIS_MARGIN;
        int bottom = VisualConstants.AXIS_MARGIN - VisualConstants.SEPARATOR_MARGIN_TOP_BOTTOM;
        setBorder(new MarginBorder(margin, margin, bottom, margin));
    }
}
