package pl.edu.agh.iisg.timeline.model;

import java.util.Random;

import org.junit.Test;

import com.google.common.base.Stopwatch;

public class CreatingTimelineDiagramDemo {

	private static final int AXES_COUNT = 10;
	private static final long AXIS_ELEMENTS_COUNT = 10L;

	private static int axesCounter = 0;
	private static long elementsCounter = 0;

	private Random rand = new Random();

	@Test
	public void createDiagramPerformanceTest() {
		Stopwatch watch = new Stopwatch();
		watch.start();
		TimelineDiagram diagram = TimelineDiagram.builder().minDateTime(1L)
				.maxDateTime(2L).build();

		for (int i = 0; i < AXES_COUNT; i++) {
			Axis axis = createSampleAxis();
			diagram.addAxis(axis);
			for (long j = 0; j < AXIS_ELEMENTS_COUNT; j++) {
				diagram.addAxisElement(createSampleAxisElement(axis));
			}
		}

		watch.stop();
		System.out.println(String.format("Elapsed time %s", watch.toString()));

	}

	private Axis createSampleAxis() {
		Axis axis = Axis.builder().name(String.format("axis%d", axesCounter++))
				.build();
		return axis;
	}

	private AxisElement createSampleAxisElement(Axis owner) {
		AxisElement element = AxisElement.builder().owner(owner)
				.name(String.format("element %d", elementsCounter++))
				.date(rand.nextLong()).build();
		return element;
	}

}
