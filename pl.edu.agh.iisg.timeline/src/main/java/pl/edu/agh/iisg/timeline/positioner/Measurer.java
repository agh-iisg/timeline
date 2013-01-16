package pl.edu.agh.iisg.timeline.positioner;

import pl.edu.agh.iisg.timeline.model.AxisElement;

public class Measurer implements IMeasurer {

    private static final int ELEMENT_GAP = 90;

    @Override
    public int getHeightOf(AxisElement element) {
        // TODO implement the real measurement method
        return ELEMENT_GAP;
    }
}
