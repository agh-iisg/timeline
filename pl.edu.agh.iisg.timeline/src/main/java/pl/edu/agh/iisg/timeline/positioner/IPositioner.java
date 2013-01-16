package pl.edu.agh.iisg.timeline.positioner;

import java.util.Collection;

import pl.edu.agh.iisg.timeline.model.AxisElement;
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
    void init(Collection<AxisElement> elements);

    int getPositionOf(AxisElement element);

    int getPositionOfSeparator(Separator separator);

    Collection<Separator> getSeparatorsByPosition(int start, int end);

    Collection<AxisElement> getElementsByPosition(int start, int end);

}
