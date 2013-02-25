package pl.edu.agh.iisg.timeline.common.util;

import pl.edu.agh.iisg.timeline.common.model.Element;

/**
 * Measures the size of elements.
 *
 * @author AGH CAST Team
 */
public interface IElementMeasurer {
    int getHeightOf(Element element);

    int getHeightOfTitle(String title);

    int getHeightOfDesc(String description);
}
