package pl.edu.agh.iisg.timeline.positioner;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.SortedMap;

import org.junit.Test;

import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.AxisElement;

public class DiscretePositionerTest {

	private DiscretePositioner positioner;

	@Test
	public void testPosition() {
		// given
		positioner = new DiscretePositioner(1000);
		Axis axis = new Axis("axis");
		AxisElement element1 = AxisElement.builder().name("element1")
				.description("description1").owner(axis).date(500L).build();
		AxisElement element2 = AxisElement.builder().name("element2")
				.description("description2").owner(axis).date(800L).build();

		// when
		SortedMap<Integer, AxisElement> res = positioner.position(Arrays.asList(
				element1, element2));

		// then
		assertEquals(element1, res.get(res.firstKey()));
		assertEquals(element2, res.get(res.lastKey()));
	}

}
