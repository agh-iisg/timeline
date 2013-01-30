package pl.edu.agh.iisg.timeline.model;

import java.util.Random;

import com.google.common.base.Stopwatch;

public class CreatingTimelineDiagramDemo {

    private static final int AXES_COUNT = 10;

    private static final long ELEMENTS_COUNT = 10L;

    private static int axesCounter = 0;

    private static long elementsCounter = 0;

    private Random rand = new Random();

    public static void main(String[] args) {
        new CreatingTimelineDiagramDemo().createDiagramPerformanceTest();
    }

    public void createDiagramPerformanceTest() {
        Stopwatch watch = new Stopwatch();
        watch.start();
        TimelineDiagram diagram = new TimelineDiagram();

        for (int i = 0; i < AXES_COUNT; i++) {
            Axis axis = createSampleAxis();
            diagram.addAxis(axis);
            for (long j = 0; j < ELEMENTS_COUNT; j++) {
                diagram.addElement(createSampleElement(axis));
            }
        }

        watch.stop();
        System.out.println(String.format("Elapsed time %s", watch.toString()));

    }

    private Axis createSampleAxis() {
        Axis axis = Axis.builder().name(String.format("axis%d", axesCounter++)).build();
        return axis;
    }

    private Element createSampleElement(Axis owner) {
        Element element = Element.builder().axis(owner).title(String.format("element %d", elementsCounter++)).date(rand.nextLong())
                .build();
        return element;
    }

}
