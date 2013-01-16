package pl.edu.agh.iisg.timeline.positioner;

import pl.edu.agh.iisg.timeline.model.AxisElement;

/**
 * Measures the size of elements.
 *
 * @author leszko
 */
public interface IMeasurer {
    int getHeightOf(AxisElement element);
}
