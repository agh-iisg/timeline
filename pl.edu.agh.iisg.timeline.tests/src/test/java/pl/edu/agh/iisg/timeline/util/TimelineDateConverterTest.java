package pl.edu.agh.iisg.timeline.util;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;


public class TimelineDateConverterTest {

	private static final long DAY_INTERVAL = 1000 * 24 * 60 * 60;
	private static final long MONTH_INTERVAL = 30 * DAY_INTERVAL;
	private static final long YEAR_INTERVAL = 365 * DAY_INTERVAL;

	private static final String DATE_TO_TEST = "07/11/1990";
	private static final String DATE_FULL_FORMAT = "\u015Aroda, 7 lis 1990 godz. 12:00";
	private static final String DATE_DAY_FORMAT = "\u015Aroda, 7 lis 1990";
	private static final String DATE_MONTH_FORMAT = "Listopad 1990";
	private static final String DATE_YEAR_FORMAT = "1990";


	private long dateToTest;

	@Before
	public void before() throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		dateToTest = df.parse(DATE_TO_TEST).getTime();
	}

	/**
	 * Checks date conversion for time intervals near one day
	 */
	@Test
	public void testGetAsStringWithDayInterval() {

		//given
		TimelineDateConverter converter1 = new TimelineDateConverter(
				DAY_INTERVAL - 1);
		TimelineDateConverter converter2 = new TimelineDateConverter(
				DAY_INTERVAL);

		//when
		String resultFull = converter1.getAsString(dateToTest);
		String resultDay = converter2.getAsString(dateToTest);

		//then
		assertEquals(resultFull, DATE_FULL_FORMAT);
		assertEquals(resultDay, DATE_DAY_FORMAT);
	}

	/**
	 * Checks date conversion for time intervals near one month
	 */
	@Test
	public void testGetAsStringWithMonthInterval() {

		//given
		TimelineDateConverter converter1 = new TimelineDateConverter(
				MONTH_INTERVAL - 1);
		TimelineDateConverter converter2 = new TimelineDateConverter(
				MONTH_INTERVAL);

		//when
		String resultDay = converter1.getAsString(dateToTest);
		String resultMonth = converter2.getAsString(dateToTest);

		//then
		assertEquals(resultDay, DATE_DAY_FORMAT);
		assertEquals(resultMonth, DATE_MONTH_FORMAT);
	}


	/**
	 * Checks date conversion for time intervals near one year
	 */
	@Test
	public void testGetAsStringWithYearInterval() {

		//given
		TimelineDateConverter converter1 = new TimelineDateConverter(
				YEAR_INTERVAL - 1);
		TimelineDateConverter converter2 = new TimelineDateConverter(
				YEAR_INTERVAL);
		//when
		String resultMonth = converter1.getAsString(dateToTest);
		String resultYear = converter2.getAsString(dateToTest);

		//then
		assertEquals(resultMonth, DATE_MONTH_FORMAT);
		assertEquals(resultYear, DATE_YEAR_FORMAT);
	}

}
