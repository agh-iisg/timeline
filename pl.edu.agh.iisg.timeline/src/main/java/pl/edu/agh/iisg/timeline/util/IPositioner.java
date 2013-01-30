package pl.edu.agh.iisg.timeline.util;

import java.util.Collection;

import pl.edu.agh.iisg.timeline.model.Element;
import pl.edu.agh.iisg.timeline.model.Separator;

/**
 * Arranger of timeline elements. Assigns a timeline position (x coordinate) to each timeline element.
 *
 * @author leszko
 *
 */
public interface IPositioner {

    /**
     * Positions elements on the timeline. Assigns an x coordinate to each timeline element.
     *
     * @param elements
     *            timeline elements
     */
    void init(Collection<Element> elements);

    int getPositionOf(Element element);

    int getPositionOfSeparator(Separator separator);

    Collection<Separator> getSeparatorsByPosition(int start, int end);

    Collection<Element> getElementsByPosition(int start, int end);

    int getMaxPosition();

}
