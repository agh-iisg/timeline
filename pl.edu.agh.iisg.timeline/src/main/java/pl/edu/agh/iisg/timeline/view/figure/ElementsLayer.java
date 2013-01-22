package pl.edu.agh.iisg.timeline.view.figure;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.MarginBorder;

import pl.edu.agh.iisg.timeline.VisualConstants;

public class ElementsLayer extends Layer {

    public ElementsLayer() {
        FlowLayout layout = new FlowLayout();
        layout.setMinorSpacing(VisualConstants.AXIS_MARGIN);
        setLayoutManager(layout);
        int margin = VisualConstants.AXIS_MARGIN;
        setBorder(new MarginBorder(0, margin, margin, margin));
    }
}
