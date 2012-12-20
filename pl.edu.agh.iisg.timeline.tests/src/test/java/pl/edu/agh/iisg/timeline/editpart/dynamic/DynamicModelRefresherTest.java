package pl.edu.agh.iisg.timeline.editpart.dynamic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.iisg.timeline.editpart.dynamic.DynamicModelRefresher;
import pl.edu.agh.iisg.timeline.editpart.dynamic.IRangeControl;
import pl.edu.agh.iisg.timeline.editpart.dynamic.ModelRefresh;
import pl.edu.agh.iisg.timeline.model.AxisElement;
import pl.edu.agh.iisg.timeline.model.Separator;
import pl.edu.agh.iisg.timeline.positioner.IPositioner;

public class DynamicModelRefresherTest {

	private DynamicModelRefresher refresher;

	private IPositioner positioner;

	private IRangeControl range;

	private AxisElement element1 = EasyMock.createMock(AxisElement.class);
	private AxisElement element2 = EasyMock.createMock(AxisElement.class);
	private AxisElement element3 = EasyMock.createMock(AxisElement.class);
	private AxisElement element4 = EasyMock.createMock(AxisElement.class);

	private Separator separator1 = EasyMock.createMock(Separator.class);
	private Separator separator2 = EasyMock.createMock(Separator.class);
	private Separator separator3 = EasyMock.createMock(Separator.class);
	private Separator separator4 = EasyMock.createMock(Separator.class);

	private Collection<AxisElement> elements1 = Arrays.asList(element1,
			element2, element3);

	private Collection<Separator> separators1 = Arrays.asList(separator1,
			separator2, separator3);

	private Collection<AxisElement> elements2 = Arrays.asList(element2,
			element3, element4);
	private Collection<Separator> separators2 = Arrays.asList(separator1,
			separator4);
	private Collection<AxisElement> elements2minus1 = Arrays.asList(element4);
	private Collection<Separator> separators2minus1 = Arrays.asList(separator4);
	private Collection<AxisElement> elements1minus2 = Arrays.asList(element1);
	private Collection<Separator> separators1minus2 = Arrays.asList(separator2,
			separator3);

	@Before
	public void setUp() {
		positioner = EasyMock.createMock(IPositioner.class);
		range = EasyMock.createMock(IRangeControl.class);
		refresher = new DynamicModelRefresher(positioner, range);
	}

	@Test
	public void testShouldNotRefresh() {
		// given
		range.setScrollPosition(EasyMock.anyInt());
		EasyMock.expect(range.isChanged()).andReturn(false);
		EasyMock.replay(range);

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

	private void assertTheSameElements(Collection<?> elements1,
			Collection<?> elements2) {
		assertTrue(elements1.containsAll(elements2));
		assertTrue(elements2.containsAll(elements1));

	}

	private void prepareMocksForInitRefresh() {
		mockRangeFirstRefresh();
		EasyMock.replay(range);

		mockPositionerFirstRefresh();
		EasyMock.replay(positioner);
	}

	@Test
	public void testChangeRangeRefresh() {
		// given
		prepareMocksForSecondRefresh();

		// when
		refresher.refresh(0);
		ModelRefresh refresh = refresher.refresh(150);

		// then
		assertTrue(refresh.shouldRefresh());
		assertTheSameElements(elements2minus1, refresh.getElementsToAdd());
		assertTheSameElements(elements1minus2, refresh.getElementsToRemove());
		assertTheSameElements(separators2minus1, refresh.getSeparatorsToAdd());
		assertTheSameElements(separators1minus2,
				refresh.getSeparatorsToRemove());
	}

	private void prepareMocksForSecondRefresh() {
		mockRangeFirstRefresh();
		mockRangeSecondRefresh();
		EasyMock.replay(range);

		mockPositionerFirstRefresh();
		mockPositionerSecondRefresh();
		EasyMock.replay(positioner);
	}

	private void mockRangeFirstRefresh() {
		range.setScrollPosition(0);
		EasyMock.expect(range.isChanged()).andReturn(true);
		EasyMock.expect(range.getStart()).andReturn(0);
		EasyMock.expect(range.getEnd()).andReturn(100);
	}

	private void mockRangeSecondRefresh() {
		range.setScrollPosition(150);
		EasyMock.expect(range.isChanged()).andReturn(true);
		EasyMock.expect(range.getStart()).andReturn(50);
		EasyMock.expect(range.getEnd()).andReturn(250);
	}

	private void mockPositionerFirstRefresh() {
		EasyMock.expect(positioner.getElementsByPosition(0, 100)).andReturn(
				elements1);
		EasyMock.expect(positioner.getSeparatorsByPosition(0, 100)).andReturn(
				separators1);
	}

	private void mockPositionerSecondRefresh() {
		EasyMock.expect(positioner.getElementsByPosition(50, 250)).andReturn(
				elements2);
		EasyMock.expect(positioner.getSeparatorsByPosition(50, 250)).andReturn(
				separators2);
	}
}
