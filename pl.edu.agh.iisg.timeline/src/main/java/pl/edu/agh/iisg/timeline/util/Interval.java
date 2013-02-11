package pl.edu.agh.iisg.timeline.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Interval {

    public enum Units {
        MILLISECONDS, SECONDS, MINUTES, HOURS, DAYS, MONTHS, YEARS;
    }

    private int value;

    private Units units;

    private static final int MONTHS_IN_YEAR = 12;

    private static final int MILLIS_IN_SECOND = 1000;

    private static final int MILLIS_IN_MINUTE = MILLIS_IN_SECOND * 60;

    private static final int MILLIS_IN_HOUR = MILLIS_IN_MINUTE * 60;

    private static final int MILLIS_IN_DAY = MILLIS_IN_HOUR * 24;

    /**
     * Default constructor.
     * @param duration duration of the interval
     * @param units units in which interval is expressed.
     */
    public Interval(int duration, Units units) {
        this.value = duration;
        this.units = units;
    }

    /**
     * Get duration of the interval.
     * @return duration.
     */
    public int getDuration() {
        return value;
    }

    /**
     *  Gets unit of the interval. See {@link Units}.
     * @return units value.
     */
    public Units getUnits() {
        return units;
    }

    /**
     * Find beginning of time period that is determined by referenceData and Interval parameters.
     * @param referenceDate starting date of one exemplary period.
     * @param givenDate date that period beginning is searched for. Can be before or after referenceDate.
     * @return new {@link Calendar} object with period beginning.
     */
    public Calendar findPeriodBeginning(Calendar referenceDate, Calendar givenDate) {
        Calendar resultCalendar = null;

        switch(units) {

        case MILLISECONDS:
            resultCalendar = new GregorianCalendar();

            long referenceMillis = referenceDate.getTimeInMillis();
            long givenMillis = givenDate.getTimeInMillis();

            // Difference between period beginning and given date.
            long diff = moduloWithNegatives((givenMillis - referenceMillis), value);
            diff = diff >= 0 ? diff : value + diff;

            resultCalendar.setTimeInMillis(givenMillis - diff);
            break;

        case SECONDS:
            resultCalendar = (Calendar)referenceDate.clone();
            resultCalendar.set(Calendar.YEAR, givenDate.get(Calendar.YEAR));
            resultCalendar.set(Calendar.MONTH, givenDate.get(Calendar.MONTH));
            resultCalendar.set(Calendar.DAY_OF_MONTH, givenDate.get(Calendar.DAY_OF_MONTH));
            resultCalendar.set(Calendar.HOUR_OF_DAY, givenDate.get(Calendar.HOUR_OF_DAY));
            resultCalendar.set(Calendar.MINUTE, givenDate.get(Calendar.MINUTE));
            resultCalendar.set(Calendar.SECOND, givenDate.get(Calendar.SECOND));

            long refSecondsSinceEpoch = referenceDate.getTimeInMillis() / MILLIS_IN_SECOND;
            long givenSecondsSinceEpoch = givenDate.getTimeInMillis() / MILLIS_IN_SECOND;

            int secondsDiff = (int)moduloWithNegatives((givenSecondsSinceEpoch - refSecondsSinceEpoch), value);
            resultCalendar.add(Calendar.SECOND, -secondsDiff);

            while (resultCalendar.after(givenDate)) {
                resultCalendar.add(Calendar.SECOND, -value);
            }
            break;

        case MINUTES:
            resultCalendar = (Calendar)referenceDate.clone();
            resultCalendar.set(Calendar.YEAR, givenDate.get(Calendar.YEAR));
            resultCalendar.set(Calendar.MONTH, givenDate.get(Calendar.MONTH));
            resultCalendar.set(Calendar.DAY_OF_MONTH, givenDate.get(Calendar.DAY_OF_MONTH));
            resultCalendar.set(Calendar.HOUR_OF_DAY, givenDate.get(Calendar.HOUR_OF_DAY));
            resultCalendar.set(Calendar.MINUTE, givenDate.get(Calendar.MINUTE));

            long refMinutesSinceEpoch = referenceDate.getTimeInMillis() / MILLIS_IN_MINUTE;
            long givenMinutesSinceEpoch = givenDate.getTimeInMillis() / MILLIS_IN_MINUTE;

            int minutesDiff = (int)moduloWithNegatives((givenMinutesSinceEpoch - refMinutesSinceEpoch), value);
            resultCalendar.add(Calendar.MINUTE, -minutesDiff);

            while (resultCalendar.after(givenDate)) {
                resultCalendar.add(Calendar.MINUTE, -value);
            }
            break;

        case HOURS:
            resultCalendar = (Calendar)referenceDate.clone();
            resultCalendar.set(Calendar.YEAR, givenDate.get(Calendar.YEAR));
            resultCalendar.set(Calendar.MONTH, givenDate.get(Calendar.MONTH));
            resultCalendar.set(Calendar.DAY_OF_MONTH, givenDate.get(Calendar.DAY_OF_MONTH));
            resultCalendar.set(Calendar.HOUR_OF_DAY, givenDate.get(Calendar.HOUR_OF_DAY));

            long refHoursSinceEpoch = referenceDate.getTimeInMillis() / MILLIS_IN_HOUR;
            long givenHoursSinceEpoch = givenDate.getTimeInMillis() / MILLIS_IN_HOUR;

            int hoursDiff = (int)moduloWithNegatives((givenHoursSinceEpoch - refHoursSinceEpoch), value);
            resultCalendar.add(Calendar.HOUR_OF_DAY, -hoursDiff);

            while (resultCalendar.after(givenDate)) {
                resultCalendar.add(Calendar.HOUR_OF_DAY, -value);
            }
            break;

        case DAYS:
            resultCalendar = (Calendar)referenceDate.clone();
            resultCalendar.set(Calendar.YEAR, givenDate.get(Calendar.YEAR));
            resultCalendar.set(Calendar.MONTH, givenDate.get(Calendar.MONTH));
            resultCalendar.set(Calendar.DAY_OF_MONTH, givenDate.get(Calendar.DAY_OF_MONTH));

            long refDaysSinceEpoch = referenceDate.getTimeInMillis() / MILLIS_IN_DAY;
            long givenDaysSinceEpoch = givenDate.getTimeInMillis() / MILLIS_IN_DAY;

            int daysDiff = (int)moduloWithNegatives((givenDaysSinceEpoch - refDaysSinceEpoch), value);
            resultCalendar.add(Calendar.DAY_OF_MONTH, -daysDiff);

            while (resultCalendar.after(givenDate)) {
                resultCalendar.add(Calendar.DAY_OF_YEAR, -value);
            }
            break;

        case MONTHS:
            resultCalendar = (Calendar)referenceDate.clone();
            resultCalendar.set(Calendar.YEAR, givenDate.get(Calendar.YEAR));
            resultCalendar.set(Calendar.MONTH, givenDate.get(Calendar.MONTH));

            long refMonthsSinceEpoch = referenceDate.get(Calendar.YEAR) * MONTHS_IN_YEAR
                    + referenceDate.get(Calendar.MONTH);
            long givenMonthsSinceEpoch = givenDate.get(Calendar.YEAR) * MONTHS_IN_YEAR
                    + givenDate.get(Calendar.MONTH);

            int monthDiff = (int)moduloWithNegatives((givenMonthsSinceEpoch - refMonthsSinceEpoch), value);
            resultCalendar.add(Calendar.MONTH, -monthDiff);

            while (resultCalendar.after(givenDate)) {
                resultCalendar.add(Calendar.MONTH, -value);
            }
            break;

        case YEARS:
            resultCalendar = (Calendar)referenceDate.clone();
            resultCalendar.set(Calendar.YEAR, givenDate.get(Calendar.YEAR));

            long refYearsSinceEpoch = referenceDate.get(Calendar.YEAR);
            long givenYearsSinceEpoch = givenDate.get(Calendar.YEAR);

            int yearsDiff = (int)moduloWithNegatives((givenYearsSinceEpoch - refYearsSinceEpoch), value);
            resultCalendar.add(Calendar.YEAR, -yearsDiff);

            while (resultCalendar.after(givenDate)) {
                resultCalendar.add(Calendar.YEAR, -value);
            }
            break;

        }


        return resultCalendar;
    }

    public Calendar findNextPeriodBeginning(Calendar givenDate) {
        Calendar resultCalendar = (Calendar)givenDate.clone();

        switch(units) {

        case MILLISECONDS:
            resultCalendar.add(Calendar.MILLISECOND, value);
            break;

        case MINUTES:
            resultCalendar.add(Calendar.MINUTE, value);
            break;

        case HOURS:
            resultCalendar.add(Calendar.HOUR, value);
            break;

        case DAYS:
            resultCalendar.add(Calendar.DAY_OF_MONTH, value);
            break;

        case MONTHS:
            resultCalendar.add(Calendar.MONTH, value);
            break;

        case YEARS:
            resultCalendar.add(Calendar.YEAR, value);
            break;

        }

        return resultCalendar;
    }

    private long moduloWithNegatives(long divident, long divisor) {
        long result = divident % divisor;
        return result >= 0 ? result: result + divisor;
    }

}
