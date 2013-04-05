package pl.edu.agh.iisg.timeline.common.editpart.dynamic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import pl.edu.agh.iisg.timeline.common.editpart.dynamic.DefaultRangeControl;

import org.junit.Test;


public class DefaultRangeControlTest {

    private final static int HEIGHT = 100;

    private final static int INERTIA = 50;

    private final static int MARGIN = 500;

    private DefaultRangeControl range = new DefaultRangeControl(HEIGHT, INERTIA, MARGIN);

    @Test
    public void testInitRange() {
        // when
        range.setScrollPosition(0);

        // then
        assertTrue(range.isChanged());
        assertEquals(MARGIN + HEIGHT, range.getEnd());
        assertEquals(0, range.getStart());
    }

    @Test
    public void changeRangeNotOutsideInertia() {
        // given
        range.setScrollPosition(0);

        // when
        range.setScrollPosition(55);

        // then
        assertFalse(range.isChanged());
        assertEquals(MARGIN + HEIGHT, range.getEnd());
        assertEquals(0, range.getStart());
    }

    @Test
    public void changeOutsideInertiaInsideLeftMargin() {
        // given
        range.setScrollPosition(0);

        // when
        range.setScrollPosition(300);

        // then
        assertTrue(range.isChanged());
        assertEquals(300 + HEIGHT + MARGIN, range.getEnd());
        assertEquals(0, range.getStart());
    }

    @Test
    public void changeOutsideInertiaOutsideLeftMargin() {
        // given
        range.setScrollPosition(0);

        // when
        range.setScrollPosition(1000);

        // then
        assertTrue(range.isChanged());
        assertEquals(1000 + HEIGHT + MARGIN, range.getEnd());
        assertEquals(1000 - MARGIN, range.getStart());
    }

    @Test
    public void changeDoubleTheSame() {
        // given
        range.setScrollPosition(0);

        // given
        range.setScrollPosition(1000);
        range.setScrollPosition(1000);

        // then
        assertFalse(range.isChanged());
        assertEquals(1000 + HEIGHT + MARGIN, range.getEnd());
        assertEquals(1000 - MARGIN, range.getStart());
    }
}
