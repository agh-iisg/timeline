package pl.edu.agh.iisg.timeline.common.util;

import java.util.Collection;

import pl.edu.agh.iisg.timeline.common.model.Element;
import pl.edu.agh.iisg.timeline.common.model.Separator;


/**
 * Arranger of timeline elements. Assigns a timeline position (x coordinate) to each timeline element.
 *
 * @author AGH CAST Team
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
