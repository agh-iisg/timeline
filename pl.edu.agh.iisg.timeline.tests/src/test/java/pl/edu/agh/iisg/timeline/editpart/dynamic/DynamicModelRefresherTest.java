package pl.edu.agh.iisg.timeline.editpart.dynamic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.iisg.timeline.model.Element;
import pl.edu.agh.iisg.timeline.model.Separator;
import pl.edu.agh.iisg.timeline.util.IPositioner;

public class DynamicModelRefresherTest {

    private DynamicModelRefresher refresher;

    private IPositioner positioner;

    private IRangeControl range;

    private Element element1 = mock(Element.class);

    private Element element2 = mock(Element.class);

    private Element element3 = mock(Element.class);

    private Element element4 = mock(Element.class);

    private Separator separator1 = mock(Separator.class);

    private Separator separator2 = mock(Separator.class);

    private Separator separator3 = mock(Separator.class);

    private Separator separator4 = mock(Separator.class);

    private Collection<Element> elements1 = Arrays.asList(element1, element2, element3);

    private Collection<Separator> separators1 = Arrays.asList(separator1, separator2, separator3);

    private Collection<Element> elements2 = Arrays.asList(element2, element3, element4);

    private Collection<Separator> separators2 = Arrays.asList(separator1, separator4);

    private Collection<Element> elements2minus1 = Arrays.asList(element4);

    private Collection<Separator> separators2minus1 = Arrays.asList(separator4);

    private Collection<Element> elements1minus2 = Arrays.asList(element1);

    private Collection<Separator> separators1minus2 = Arrays.asList(separator2, separator3);

    @Before
    public void setUp() {
        positioner = mock(IPositioner.class);
        range = mock(IRangeControl.class);
        refresher = new DynamicModelRefresher(positioner, range);
    }

    @Test
    public void testShouldNotRefresh() {
        // given
        when(range.isChanged()).thenReturn(false);

        // when
        ModelRefresh refresh = refresher.refresh(0);

        // then
        assertFalse(refresh.shouldRefresh());
        assertTrue(refresh.getElementsToAdd().isEmpty());
        assertTrue(refresh.getElementsToRemove().isEmpty());
        assertTrue(refresh.getSeparatorsToAdd().isEmpty());
        assertTrue(refresh.getSeparatorsToRemove().isEmpty());
    }

    @Test
    public void testInitRefresh() {
        // given
        prepareMocksForInitRefresh();

        // when
        ModelRefresh refresh = refresher.refresh(0);

        // then
        assertTrue(refresh.shouldRefresh());
        assertTheSameElements(elements1, refresh.getElementsToAdd());
        assertTrue(refresh.getElementsToRemove().isEmpty());
        assertTheSameElements(separators1, refresh.getSeparatorsToAdd());
        assertTrue(refresh.getSeparatorsToRemove().isEmpty());
    }

    private void assertTheSameElements(Collection<?> elements1, Collection<?> elements2) {
        assertTrue(elements1.containsAll(elements2));
        assertTrue(elements2.containsAll(elements1));

    }

    private void prepareMocksForInitRefresh() {
        mockRangeFirstRefresh();

        mockPositionerFirstRefresh();
    }

    @Test
    public void testChangeRangeRefresh() {
        // given
        prepareMocksForTwoRefreshes();

        // when
        refresher.refresh(0);
        ModelRefresh refresh = refresher.refresh(150);

        // then
        assertTrue(refresh.shouldRefresh());
        assertTheSameElements(elements2minus1, refresh.getElementsToAdd());
        assertTheSameElements(elements1minus2, refresh.getElementsToRemove());
        assertTheSameElements(separators2minus1, refresh.getSeparatorsToAdd());
        assertTheSameElements(separators1minus2, refresh.getSeparatorsToRemove());
    }

    private void prepareMocksForTwoRefreshes() {
        mockRangeForTwoRefreshes();

        mockPositionerFirstRefresh();
        mockPositionerSecondRefresh();
    }

    private void mockRangeFirstRefresh() {
        when(range.isChanged()).thenReturn(true);
        when(range.getStart()).thenReturn(0);
        when(range.getEnd()).thenReturn(100);
    }

    private void mockRangeForTwoRefreshes() {
        when(range.isChanged()).thenReturn(true, true);
        when(range.getStart()).thenReturn(0, 50);
        when(range.getEnd()).thenReturn(100, 250);
    }

    private void mockPositionerFirstRefresh() {
        when(positioner.getElementsByPosition(0, 100)).thenReturn(elements1);
        when(positioner.getSeparatorsByPosition(0, 100)).thenReturn(separators1);
    }

    private void mockPositionerSecondRefresh() {
        when(positioner.getElementsByPosition(50, 250)).thenReturn(elements2);
        when(positioner.getSeparatorsByPosition(50, 250)).thenReturn(separators2);
    }
}
