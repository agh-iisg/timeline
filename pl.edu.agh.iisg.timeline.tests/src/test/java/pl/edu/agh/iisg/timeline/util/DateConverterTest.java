package pl.edu.agh.iisg.timeline.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import pl.edu.agh.iisg.timeline.util.Interval.Unit;

public class DateConverterTest {

    private static final String DATE_MILLISECOND_FORMAT = "\u015Aroda, 7 listopada 1990 godz. 12:10:05:000";

    private static final String DATE_SECOND_FORMAT = "\u015Aroda, 7 listopada 1990 godz. 12:10:05";

    private static final String DATE_HOUR_FORMAT = "\u015Aroda, 7 listopada 1990 godz. 12:10";

    private static final String DATE_DAY_FORMAT = "\u015Aroda, 7 listopada 1990";

    private static final String DATE_MONTH_FORMAT = "Listopad 1990";

    private static final String DATE_YEAR_FORMAT = "1990";

    private static final Calendar dateToTest = new GregorianCalendar(1990, Calendar.NOVEMBER, 07, 12, 10, 5);

    /**
     * Checks date conversion for millisecond intervals.
     */
    @Test
    public void testGetAsStringWithMillisecondInterval() {

        // given
        DateConverter converter = new DateConverter(new Interval(1, Unit.MILLISECOND));

        // when
        String result = converter.asString(dateToTest);

        // then
        assertEquals(DATE_MILLISECOND_FORMAT, result);
    }

    /**
     * Checks date conversion for second intervals.
     */
    @Test
    public void testGetAsStringWithSecondInterval() {

        // given
        DateConverter converter = new DateConverter(new Interval(1, Unit.SECOND));

        // when
        String result = converter.asString(dateToTest);

        // then
        assertEquals(DATE_SECOND_FORMAT, result);
    }

    /**
     * Checks date conversion for minute intervals.
     */
    @Test
    public void testGetAsStringWithMinuteInterval() {

        // given
        DateConverter converter = new DateConverter(new Interval(1, Unit.MINUTE));

        // when
        String result = converter.asString(dateToTest);

        // then
        assertEquals(DATE_HOUR_FORMAT, result);
    }

    /**
     * Checks date conversion for hour intervals.
     */
    @Test
    public void testGetAsStringWithHourInterval() {

        // given
        DateConverter converter = new DateConverter(new Interval(1, Unit.HOUR));

        // when
        String result = converter.asString(dateToTest);

        // then
        assertEquals(DATE_HOUR_FORMAT, result);
    }

    /**
     * Checks date conversion for day intervals.
     */
    @Test
    public void testGetAsStringWithDayInterval() {

        // given
        DateConverter converter = new DateConverter(new Interval(1, Unit.DAY));

        // when
        String result = converter.asString(dateToTest);

        // then
        assertEquals(DATE_DAY_FORMAT, result);
    }

    /**
     * Checks date conversion for month intervals.
     */
    @Test
    public void testGetAsStringWithMonthInterval() {

        // given
        DateConverter converter = new DateConverter(new Interval(1, Unit.MONTH));

        // when
        String result = converter.asString(dateToTest);

        // then
        assertEquals(DATE_MONTH_FORMAT, result);
    }

    /**
     * Checks date conversion for year intervals.
     */
    @Test
    public void testGetAsStringWithYearInterval() {

        // given
        DateConverter converter = new DateConverter(new Interval(1, Unit.YEAR));

        // when
        String result = converter.asString(dateToTest);

        // then
        assertEquals(DATE_YEAR_FORMAT, result);
    }

}
