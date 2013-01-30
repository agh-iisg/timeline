package pl.edu.agh.iisg.timeline.util;

import pl.edu.agh.iisg.timeline.model.Element;

/**
 * Measures the size of elements.
 *
 * @author leszko
 */
public interface IElementMeasurer {
    int getHeightOf(Element element);

    int getHeightOfTitle(String title);

    int getHeightOfDesc(String description);
}
