package pl.edu.agh.iisg.timeline.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import pl.edu.agh.iisg.timeline.common.model.Separator;

import pl.edu.agh.iisg.timeline.common.util.ISeparatorFactory;

import pl.edu.agh.iisg.timeline.common.util.IElementMeasurer;

import pl.edu.agh.iisg.timeline.common.model.Element;

import pl.edu.agh.iisg.timeline.common.model.Axis;

import pl.edu.agh.iisg.timeline.common.util.Interval.Unit;

import pl.edu.agh.iisg.timeline.common.util.Interval;

import pl.edu.agh.iisg.timeline.common.util.DiscretePositioner;

import java.util.Arrays;
import java.util.Calendar;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


public class DiscretePositionerTest {

    private DiscretePositioner positioner;

    private static final int AXIS_NUMBER = 2;

    @Test
    public void testPosition() {
        // given
        Interval interval = new Interval(1000, Unit.MILLISECOND);
        positioner = new DiscretePositioner(interval, mockMeasurer(), mockSeparatorFactory());
        Axis axis = new Axis("axis"); //$NON-NLS-1$
        Element element1 = Element.builder().title("element1").description("description1").axis(axis).date(500L).build();  //$NON-NLS-1$//$NON-NLS-2$
        Element element2 = Element.builder().title("element2").description("description2").axis(axis).date(800L).build(); //$NON-NLS-1$ //$NON-NLS-2$

        // when
        positioner.init(Arrays.asList(element1, element2));

        // then
        assertTrue(positioner.getPositionOf(element1) < positioner.getPositionOf(element2));
        assertEquals(1, positioner.getSeparatorsByPosition(0, 1000).size());
        assertTrue(positioner.getSeparatorsByPosition(0, 1000).iterator().next().getDate().getTimeInMillis() < positioner
                .getPositionOf(element1));
    }

    private IElementMeasurer mockMeasurer() {
        IElementMeasurer measurer = mock(IElementMeasurer.class);
        when(measurer.getHeightOf(any(Element.class))).thenReturn(50);
        return measurer;
    }

    private ISeparatorFactory mockSeparatorFactory() {
        ISeparatorFactory separatorFactory = mock(ISeparatorFactory.class);
        final ArgumentCaptor<Calendar> captor = ArgumentCaptor.forClass(Calendar.class);
        when(separatorFactory.newSeparator(captor.capture())).thenAnswer(new Answer<Separator>() {

            @Override
            public Separator answer(InvocationOnMock invocation) throws Throwable {
                return new Separator(captor.getValue(), AXIS_NUMBER);
            }
        });
        return separatorFactory;
    }
}
