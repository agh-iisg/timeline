package pl.edu.agh.iisg.timeline.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import pl.edu.agh.iisg.timeline.util.Interval.Units;

public class IntervalTest {

	@Test
	public void testMillis() {
		// given
		Interval interval = new Interval(500, Units.MILLISECONDS);
		Calendar referenceDate = new GregorianCalendar();
		referenceDate.setTimeInMillis(1300);

		Calendar givenDate1 = new GregorianCalendar();
		givenDate1.setTimeInMillis(1000);

		Calendar givenDate2 = new GregorianCalendar();
		givenDate2.setTimeInMillis(1500);

		Calendar givenDate3 = new GregorianCalendar();
		givenDate3.setTimeInMillis(2000);

		// when
		Calendar periodBeginning1 = interval.findPeriodBeginning(referenceDate, givenDate1);
		Calendar periodBeginning2 = interval.findPeriodBeginning(referenceDate, givenDate2);
		Calendar periodBeginning3 = interval.findPeriodBeginning(referenceDate, givenDate3);

		// then
		assertEquals(periodBeginning1.getTimeInMillis(), 800);
		assertEquals(periodBeginning2.getTimeInMillis(), 1300);
		assertEquals(periodBeginning3.getTimeInMillis(), 1800);
	}

	@Test
	public void testSeconds() {
		//given
		Interval interval = new Interval(30, Units.SECONDS);

		// 1 Feb 2013, 10:05
		Calendar referenceDate = new GregorianCalendar(2013, Calendar.MARCH, 1, 10, 0, 5);

		// 5 Feb 2013, 15:53 should give 5 Feb 2013, 15:35
		Calendar givenDate1 = new GregorianCalendar(2013, Calendar.FEBRUARY, 5, 15, 0, 43);

		// 1 Feb 2013, 10:10 should give 1 Feb 2013, 10:05
		Calendar givenDate2 = new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 10, 0, 10);

		// 20 Jan 2013, 17:00 should give 20 Jan 2013, 16:35
		Calendar givenDate3 = new GregorianCalendar(2013, Calendar.JANUARY, 20, 17, 0, 0);

		// when
		Calendar periodBeginning1 = interval.findPeriodBeginning(referenceDate, givenDate1);
		Calendar periodBeginning2 = interval.findPeriodBeginning(referenceDate, givenDate2);
		Calendar periodBeginning3 = interval.findPeriodBeginning(referenceDate, givenDate3);

		// then
		assertEquals(periodBeginning1, new GregorianCalendar(2013, Calendar.FEBRUARY, 5, 15, 0, 35));
		assertEquals(periodBeginning2, new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 10, 0, 5));
		assertEquals(periodBeginning3, new GregorianCalendar(2013, Calendar.JANUARY, 20, 16, 59, 35));
	}

	@Test
	public void testMinutes() {
		//given
		Interval interval = new Interval(30, Units.MINUTES);

		// 1 Feb 2013, 10:05
		Calendar referenceDate = new GregorianCalendar(2013, Calendar.MARCH, 1, 10, 5);

		// 5 Feb 2013, 15:53 should give 5 Feb 2013, 15:35
		Calendar givenDate1 = new GregorianCalendar(2013, Calendar.FEBRUARY, 5, 15, 43);

		// 1 Feb 2013, 10:10 should give 1 Feb 2013, 10:05
		Calendar givenDate2 = new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 10, 10);

		// 20 Jan 2013, 17:00 should give 20 Jan 2013, 16:35
		Calendar givenDate3 = new GregorianCalendar(2013, Calendar.JANUARY, 20, 17, 0);

		// when
		Calendar periodBeginning1 = interval.findPeriodBeginning(referenceDate, givenDate1);
		Calendar periodBeginning2 = interval.findPeriodBeginning(referenceDate, givenDate2);
		Calendar periodBeginning3 = interval.findPeriodBeginning(referenceDate, givenDate3);

		// then
		assertEquals(periodBeginning1, new GregorianCalendar(2013, Calendar.FEBRUARY, 5, 15, 35));
		assertEquals(periodBeginning2, new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 10, 5));
		assertEquals(periodBeginning3, new GregorianCalendar(2013, Calendar.JANUARY, 20, 16, 35));
	}

	@Test
	public void testHours() {
		//given
		Interval interval = new Interval(2, Units.HOURS);
		Interval interval2 = new Interval(24, Units.HOURS);

		// 1 Feb 2013, 23:00
		Calendar referenceDate = new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 23, 0);

		// 1 Jan 2011, 00:00
		Calendar referenceDate2 = new GregorianCalendar(2012, Calendar.MAY, 1, 0, 0, 0);

		// 1 Feb 2013, 23:05 should give 1 Feb 2013, 23:00
		Calendar givenDate1 = new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 23, 5);

		// 2 Feb 2013, 00:30 should give 1 Feb 2013, 23:00
		Calendar givenDate2 = new GregorianCalendar(2013, Calendar.FEBRUARY, 2, 0, 30);

		// 2 Feb 2013, 05:00 should give 2 Feb 2013, 05:00
		Calendar givenDate3 = new GregorianCalendar(2013, Calendar.FEBRUARY, 2, 5, 0);

		// 2 Feb 2013, 05:00 should give 2 Feb 2013, 05:00
		Calendar givenDate4 = new GregorianCalendar(2012, Calendar.AUGUST, 28, 20, 0);

		// when
		Calendar periodBeginning1 = interval.findPeriodBeginning(referenceDate, givenDate1);
		Calendar periodBeginning2 = interval.findPeriodBeginning(referenceDate, givenDate2);
		Calendar periodBeginning3 = interval.findPeriodBeginning(referenceDate, givenDate3);

		Calendar periodBeginning4 = interval2.findPeriodBeginning(referenceDate2, givenDate4);

		// then
		assertEquals(periodBeginning1, new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 23, 0));
		assertEquals(periodBeginning2, new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 23, 0));
		assertEquals(periodBeginning3, new GregorianCalendar(2013, Calendar.FEBRUARY, 2, 5, 0));

		assertEquals(periodBeginning4, new GregorianCalendar(2012, Calendar.AUGUST, 28, 0, 0));
	}

	@Test
	public void testDays() {
		//given
		Interval interval = new Interval(1, Units.DAYS);

		// 1 Feb 2013, 10:00
		Calendar referenceDate = new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 10, 0);

		// 1 Feb 2013, 15:00 should give 1 Feb 2013, 10:00
		Calendar givenDate1 = new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 15, 0);

		// 1 Feb 2013, 09:50 should give 31 Jan 2013, 10:00
		Calendar givenDate2 = new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 9, 50);

		// 3 Feb 2013, 05:00 should give 2 Feb 2013, 10:00
		Calendar givenDate3 = new GregorianCalendar(2013, Calendar.FEBRUARY, 3, 5, 0);

		// 3 Aug 2013, 15:00 should give 3 Aug 2013, 10:00
		Calendar givenDate4 = new GregorianCalendar(2013, Calendar.AUGUST, 3, 10, 0);

		// when
		Calendar periodBeginning1 = interval.findPeriodBeginning(referenceDate, givenDate1);
		Calendar periodBeginning2 = interval.findPeriodBeginning(referenceDate, givenDate2);
		Calendar periodBeginning3 = interval.findPeriodBeginning(referenceDate, givenDate3);
		Calendar periodBeginning4 = interval.findPeriodBeginning(referenceDate, givenDate4);

		// then
		assertEquals(periodBeginning1, new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 10, 0));
		assertEquals(periodBeginning2, new GregorianCalendar(2013, Calendar.JANUARY, 31, 10, 0));
		assertEquals(periodBeginning3, new GregorianCalendar(2013, Calendar.FEBRUARY, 2, 10, 0));
		assertEquals(periodBeginning4, new GregorianCalendar(2013, Calendar.AUGUST, 3, 10, 0));
	}

	@Test
	public void testMonths() {
		//given
		Interval interval = new Interval(2, Units.MONTHS);

		// 1 Feb 2013, 10:00
		Calendar referenceDate = new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 10, 0);

		// 15 Feb 2013, 15:00 should give 1 Feb 2013, 10:00
		Calendar givenDate1 = new GregorianCalendar(2013, Calendar.FEBRUARY, 15, 15, 0);

		// 1 Feb 2013, 09:50 should give 1 Dec 2012, 10:00
		Calendar givenDate2 = new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 9, 50);

		// 30 Sep 2013, 10:00 should give 1 Aug 2013, 10:00
		Calendar givenDate3 = new GregorianCalendar(2013, Calendar.SEPTEMBER, 30, 10, 0);

		// when
		Calendar periodBeginning1 = interval.findPeriodBeginning(referenceDate, givenDate1);
		Calendar periodBeginning2 = interval.findPeriodBeginning(referenceDate, givenDate2);
		Calendar periodBeginning3 = interval.findPeriodBeginning(referenceDate, givenDate3);

		// then
		assertEquals(periodBeginning1, new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 10, 0));
		assertEquals(periodBeginning2, new GregorianCalendar(2012, Calendar.DECEMBER, 1, 10, 0));
		assertEquals(periodBeginning3, new GregorianCalendar(2013, Calendar.AUGUST, 1, 10, 0));
	}

	@Test
	public void testYears() {
		//given
		Interval interval = new Interval(1, Units.YEARS);

		// 1 Feb 2013, 10:00
		Calendar referenceDate = new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 10, 0);

		// 15 Feb 2013, 15:00 should give 1 Feb 2013, 10:00
		Calendar givenDate1 = new GregorianCalendar(2013, Calendar.FEBRUARY, 15, 15, 0);

		// 1 Feb 2013, 09:50 should give 1 Feb 2012, 10:00
		Calendar givenDate2 = new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 9, 50);

		// 1 Aug 2013, 10:00 should give 1 Feb 2013, 10:00
		Calendar givenDate3 = new GregorianCalendar(2013, Calendar.AUGUST, 1, 10, 0);

		// when
		Calendar periodBeginning1 = interval.findPeriodBeginning(referenceDate, givenDate1);
		Calendar periodBeginning2 = interval.findPeriodBeginning(referenceDate, givenDate2);
		Calendar periodBeginning3 = interval.findPeriodBeginning(referenceDate, givenDate3);

		// then
		assertEquals(periodBeginning1, new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 10, 0));
		assertEquals(periodBeginning2, new GregorianCalendar(2012, Calendar.FEBRUARY, 1, 10, 0));
		assertEquals(periodBeginning3, new GregorianCalendar(2013, Calendar.FEBRUARY, 1, 10, 0));
	}


	@Test
	public void testNextPeriodMonths() {
		//given
		Interval interval = new Interval(1, Units.MONTHS);
		Interval interval2 = new Interval(6, Units.MONTHS);

		// 15 Dec 2012, 15:00 should give 15 Jan 2013, 15:00
		Calendar givenDate1 = new GregorianCalendar(2012, Calendar.DECEMBER, 15, 15, 0);

		// when
		Calendar nextPeriodBeginning1 = interval.findNextPeriodBeginning(givenDate1);
		Calendar nextPeriodBeginning2 = interval2.findNextPeriodBeginning(givenDate1);

		// then
		assertEquals(nextPeriodBeginning1, new GregorianCalendar(2013, Calendar.JANUARY, 15, 15, 0));
		assertEquals(nextPeriodBeginning2, new GregorianCalendar(2013, Calendar.JUNE, 15, 15, 0));
	}

}
