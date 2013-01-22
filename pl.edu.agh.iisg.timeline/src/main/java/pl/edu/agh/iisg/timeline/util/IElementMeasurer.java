package pl.edu.agh.iisg.timeline.util;

import pl.edu.agh.iisg.timeline.model.AxisElement;

/**
 * Measures the size of elements.
 *
 * @author leszko
 */
public interface IElementMeasurer {
    int getHeightOf(AxisElement element);

    int getHeightOfTitle(String title);

    int getHeightOfDescription(String description);
}
