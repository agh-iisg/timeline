package pl.edu.agh.iisg.timeline.positioner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.Test;

import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.AxisElement;
import pl.edu.agh.iisg.timeline.model.ISeparatorFactory;
import pl.edu.agh.iisg.timeline.model.Separator;

public class DiscretePositionerTest {

    private DiscretePositioner positioner;

    private static final int AXIS_NUMBER = 2;

    @Test
    public void testPosition() {
        // given
        positioner = new DiscretePositioner(1000, mockMeasurer(), mockSeparatorFactory());
        Axis axis = new Axis("axis");
        AxisElement element1 = AxisElement.builder().name("element1").description("description1").owner(axis).date(500L).build();
        AxisElement element2 = AxisElement.builder().name("element2").description("description2").owner(axis).date(800L).build();

        // when
        positioner.init(Arrays.asList(element1, element2));

        // then
        assertTrue(positioner.getPositionOf(element1) < positioner.getPositionOf(element2));
        assertEquals(1, positioner.getSeparatorsByPosition(0, 1000).size());
        assertTrue(positioner.getSeparatorsByPosition(0, 1000).iterator().next().getValue() < positioner.getPositionOf(element1));
    }

    private IMeasurer mockMeasurer() {
        IMeasurer measurer = EasyMock.createMock(IMeasurer.class);
        EasyMock.expect(measurer.getHeightOf(EasyMock.anyObject(AxisElement.class))).andReturn(50).anyTimes();
        EasyMock.replay(measurer);
        return measurer;
    }

    private ISeparatorFactory mockSeparatorFactory() {
        ISeparatorFactory separatorFactory = EasyMock.createMock(ISeparatorFactory.class);
        final Capture<Long> capture = new Capture<Long>();
        EasyMock.expect(separatorFactory.newSeparator(EasyMock.captureLong(capture))).andAnswer(new IAnswer<Separator>() {
            @Override
            public Separator answer() throws Throwable {
                return new Separator(capture.getValue(), AXIS_NUMBER);
            }
        }).anyTimes();
        EasyMock.replay(separatorFactory);
        return separatorFactory;
    }
}
