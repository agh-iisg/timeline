package pl.edu.agh.iisg.timeline.positioner;

import java.util.Collection;
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
	 */
	void position(Collection<AxisElement> elements);

	int getPositionOf(AxisElement element);

	int getPositionOfSeparator(Long separator);

	SortedMap<Integer, Long> getSeparators();

}
