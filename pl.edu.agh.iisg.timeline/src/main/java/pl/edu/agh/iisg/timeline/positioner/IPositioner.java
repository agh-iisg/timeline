package pl.edu.agh.iisg.timeline.positioner;

import java.util.List;
import java.util.SortedMap;

import pl.edu.agh.iisg.timeline.model.AxisElement;

/**
 * Arranger of timeline elements. Assigns a timeline position (x coordinate) to
 * each timeline element.
 *
 * @author leszko
 *
 */
public interface IPositioner {

	/**
	 * Positions elements on the timeline. Assigns an x coordinate to each
	 * timeline element.
	 *
	 * @param elements
	 *            timeline elements
	 * @return positions of timeline elements
	 */
	SortedMap<Long, AxisElement> position(List<AxisElement> elements);

}
