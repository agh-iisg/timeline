package pl.edu.agh.iisg.timeline.view.figure;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.text.FlowPage;

import pl.edu.agh.iisg.timeline.VisualConstants;

public class AxisFlowPage extends FlowPage {

	@Override
	public Dimension getPreferredSize(int width, int h) {
		return super.getPreferredSize(VisualConstants.AXIS_LABEL_WIDTH, -1);
	}
}
