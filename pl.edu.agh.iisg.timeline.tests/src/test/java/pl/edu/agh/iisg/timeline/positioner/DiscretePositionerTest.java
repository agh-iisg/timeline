package pl.edu.agh.iisg.timeline.positioner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

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
		positioner.position(Arrays.asList(element1, element2));

		// then
		assertTrue(positioner.getPositionOf(element1) < positioner
				.getPositionOf(element2));
		assertEquals(1, positioner.getSeparators().size());
		assertTrue(positioner.getSeparators().firstKey() < positioner
				.getPositionOf(element1));
	}

}
