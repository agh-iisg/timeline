package pl.edu.agh.iisg.timeline.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
	public void testMinutes() {
		//given
		Interval interval = new Interval(30, Units.MINUTES);

		// 1 Feb 2013, 10:05
		Calendar referenceDate = new GregorianCalendar(2013, 2, 1, 10, 0);

		// 5 Feb 2013, 15:53 should give 5 Feb 2013, 15:35
		Calendar givenDate1 = new GregorianCalendar(2013, 2, 5, 15, 13);

		// 1 Feb 2013, 10:10 should give 5 Feb 2013, 10:05
		Calendar givenDate2 = new GregorianCalendar(2013, 2, 1, 10, 10);

		// 20 Jan 2013, 17:00 should give 20 Jan 2013, 16:35
		Calendar givenDate3 = new GregorianCalendar(2013, 1, 20, 17, 0);

		// when
		Calendar periodBeginning1 = interval.findPeriodBeginning(referenceDate, givenDate1);
		Calendar periodBeginning2 = interval.findPeriodBeginning(referenceDate, givenDate2);
		Calendar periodBeginning3 = interval.findPeriodBeginning(referenceDate, givenDate3);

		// then
		assertEquals(periodBeginning1, new GregorianCalendar(2013, 2, 5, 15, 35));
		assertEquals(periodBeginning2, new GregorianCalendar(2013, 2, 5, 10, 5));
		assertEquals(periodBeginning3, new GregorianCalendar(2013, 1, 20, 16, 35));
	}

	@Test
	public void testHours() {

	}

	@Test
	public void testDays() {

	}

	@Test
	public void testMonths() {

	}

	@Test
	public void testYears() {

	}

}
