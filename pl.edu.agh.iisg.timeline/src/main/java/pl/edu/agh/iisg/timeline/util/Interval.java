package pl.edu.agh.iisg.timeline.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Interval {

    public enum Unit {
        MILLISECOND, SECOND, MINUTE, HOUR, DAY, MONTH, YEAR;
    }

    private int duration;

    private Unit unit;

    private static final int MONTHS_IN_YEAR = 12;

    private static final int MILLIS_IN_SECOND = 1000;

    private static final int MILLIS_IN_MINUTE = MILLIS_IN_SECOND * 60;

    private static final int MILLIS_IN_HOUR = MILLIS_IN_MINUTE * 60;

    private static final int MILLIS_IN_DAY = MILLIS_IN_HOUR * 24;

    /**
     * Default constructor.
     *
     * @param duration
     *            duration of the interval
     * @param units
     *            units in which interval is expressed.
     */
    public Interval(int duration, Unit units) {
        this.duration = duration;
        this.unit = units;
    }

    /**
     * Get duration of the interval.
     *
     * @return duration.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Gets unit of the interval. See {@link Unit}.
     *
     * @return unit value.
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Find beginning of time period that is determined by referenceData and Interval parameters.
     *
     * @param referenceDate
     *            starting date of one exemplary period.
     * @param givenDate
     *            date that period beginning is searched for. Can be before or after referenceDate.
     * @return new {@link Calendar} object with period beginning.
     */
    public Calendar findPeriodBeginning(Calendar referenceDate, Calendar givenDate) {

        switch (unit) {
            case MILLISECOND:
            	return findPeriodBeginningForMilliseconds(referenceDate, givenDate);

            case SECOND:
            	return findPeriodBeginningForSeconds(referenceDate, givenDate);

            case MINUTE:
                return findPeriodBeginningForMinutes(referenceDate, givenDate);

            case HOUR:
            	return findPeriodBeginningForHours(referenceDate, givenDate);

            case DAY:
            	return findPeriodBeginningForDays(referenceDate, givenDate);

            case MONTH:
            	return findPeriodBeginningForMonths(referenceDate, givenDate);

            case YEAR:
            	return findPeriodBeginningForYears(referenceDate, givenDate);

            default:
            	throw new UnsupportedOperationException("Unit support not implemented.");
        }
    }

    private Calendar findPeriodBeginningForMilliseconds(Calendar referenceDate, Calendar givenDate) {
    	Calendar resultCalendar = new GregorianCalendar();

        long referenceMillis = referenceDate.getTimeInMillis();
        long givenMillis = givenDate.getTimeInMillis();

        // difference between period beginning and given date
        long diff = moduloWithNegatives((givenMillis - referenceMillis), duration);
        diff = diff >= 0 ? diff : duration + diff;

        resultCalendar.setTimeInMillis(givenMillis - diff);
        return resultCalendar;
    }

    private Calendar findPeriodBeginningForSeconds(Calendar referenceDate, Calendar givenDate) {
    	Calendar resultCalendar = (Calendar)referenceDate.clone();
        resultCalendar.set(Calendar.YEAR, givenDate.get(Calendar.YEAR));
        resultCalendar.set(Calendar.MONTH, givenDate.get(Calendar.MONTH));
        resultCalendar.set(Calendar.DAY_OF_MONTH, givenDate.get(Calendar.DAY_OF_MONTH));
        resultCalendar.set(Calendar.HOUR_OF_DAY, givenDate.get(Calendar.HOUR_OF_DAY));
        resultCalendar.set(Calendar.MINUTE, givenDate.get(Calendar.MINUTE));
        resultCalendar.set(Calendar.SECOND, givenDate.get(Calendar.SECOND));

        // compute and subtract difference between given date and period beginning
        long refSecondsSinceEpoch = referenceDate.getTimeInMillis() / MILLIS_IN_SECOND;
        long givenSecondsSinceEpoch = givenDate.getTimeInMillis() / MILLIS_IN_SECOND;

        int secondsDiff = (int)moduloWithNegatives((givenSecondsSinceEpoch - refSecondsSinceEpoch), duration);
        resultCalendar.add(Calendar.SECOND, -secondsDiff);

        // if computed period beginning is still after given date, take the previous one
        if (resultCalendar.after(givenDate)) {
        	resultCalendar.add(Calendar.SECOND, -duration);
        }

        return resultCalendar;
    }

    private Calendar findPeriodBeginningForMinutes(Calendar referenceDate, Calendar givenDate) {
    	Calendar resultCalendar = (Calendar)referenceDate.clone();
        resultCalendar.set(Calendar.YEAR, givenDate.get(Calendar.YEAR));
        resultCalendar.set(Calendar.MONTH, givenDate.get(Calendar.MONTH));
        resultCalendar.set(Calendar.DAY_OF_MONTH, givenDate.get(Calendar.DAY_OF_MONTH));
        resultCalendar.set(Calendar.HOUR_OF_DAY, givenDate.get(Calendar.HOUR_OF_DAY));
        resultCalendar.set(Calendar.MINUTE, givenDate.get(Calendar.MINUTE));

        // compute and subtract difference between given date and period beginning
        long refMinutesSinceEpoch = referenceDate.getTimeInMillis() / MILLIS_IN_MINUTE;
        long givenMinutesSinceEpoch = givenDate.getTimeInMillis() / MILLIS_IN_MINUTE;

        int minutesDiff = (int)moduloWithNegatives((givenMinutesSinceEpoch - refMinutesSinceEpoch), duration);
        resultCalendar.add(Calendar.MINUTE, -minutesDiff);

        // if computed period beginning is still after given date, take the previous one
        if (resultCalendar.after(givenDate)) {
            resultCalendar.add(Calendar.MINUTE, -duration);
        }

        return resultCalendar;
    }

    private Calendar findPeriodBeginningForHours(Calendar referenceDate, Calendar givenDate) {
    	Calendar resultCalendar = (Calendar)referenceDate.clone();
        resultCalendar.set(Calendar.YEAR, givenDate.get(Calendar.YEAR));
        resultCalendar.set(Calendar.MONTH, givenDate.get(Calendar.MONTH));
        resultCalendar.set(Calendar.DAY_OF_MONTH, givenDate.get(Calendar.DAY_OF_MONTH));
        resultCalendar.set(Calendar.HOUR_OF_DAY, givenDate.get(Calendar.HOUR_OF_DAY));

        // compute and subtract difference between given date and period beginning
        long refHoursSinceEpoch = referenceDate.getTimeInMillis() / MILLIS_IN_HOUR;
        long givenHoursSinceEpoch = givenDate.getTimeInMillis() / MILLIS_IN_HOUR;

        int hoursDiff = (int)moduloWithNegatives((givenHoursSinceEpoch - refHoursSinceEpoch), duration);
        resultCalendar.add(Calendar.HOUR_OF_DAY, -hoursDiff);

        // if computed period beginning is still after given date, take the previous one
        if (resultCalendar.after(givenDate)) {
            resultCalendar.add(Calendar.HOUR_OF_DAY, -duration);
        }

        return resultCalendar;
    }

    private Calendar findPeriodBeginningForDays(Calendar referenceDate, Calendar givenDate) {
    	Calendar resultCalendar = (Calendar)referenceDate.clone();
        resultCalendar.set(Calendar.YEAR, givenDate.get(Calendar.YEAR));
        resultCalendar.set(Calendar.MONTH, givenDate.get(Calendar.MONTH));
        resultCalendar.set(Calendar.DAY_OF_MONTH, givenDate.get(Calendar.DAY_OF_MONTH));

        // compute and subtract difference between given date and period beginning
        long refDaysSinceEpoch = referenceDate.getTimeInMillis() / MILLIS_IN_DAY;
        long givenDaysSinceEpoch = givenDate.getTimeInMillis() / MILLIS_IN_DAY;

        int daysDiff = (int)moduloWithNegatives((givenDaysSinceEpoch - refDaysSinceEpoch), duration);
        resultCalendar.add(Calendar.DAY_OF_MONTH, -daysDiff);

        // if computed period beginning is still after given date, take the previous one
        if (resultCalendar.after(givenDate)) {
            resultCalendar.add(Calendar.DAY_OF_YEAR, -duration);
        }

        return resultCalendar;
    }

    private Calendar findPeriodBeginningForMonths(Calendar referenceDate, Calendar givenDate) {
    	 Calendar resultCalendar = (Calendar)referenceDate.clone();
         resultCalendar.set(Calendar.YEAR, givenDate.get(Calendar.YEAR));
         resultCalendar.set(Calendar.MONTH, givenDate.get(Calendar.MONTH));

         // compute and subtract difference between given date and period beginning
         long refMonthsSinceEpoch = referenceDate.get(Calendar.YEAR) * MONTHS_IN_YEAR + referenceDate.get(Calendar.MONTH);
         long givenMonthsSinceEpoch = givenDate.get(Calendar.YEAR) * MONTHS_IN_YEAR + givenDate.get(Calendar.MONTH);

         int monthDiff = (int)moduloWithNegatives((givenMonthsSinceEpoch - refMonthsSinceEpoch), duration);
         resultCalendar.add(Calendar.MONTH, -monthDiff);

         // if computed period beginning is still after given date, take the previous one
         if (resultCalendar.after(givenDate)) {
             resultCalendar.add(Calendar.MONTH, -duration);
         }

         return resultCalendar;
    }

	private Calendar findPeriodBeginningForYears(Calendar referenceDate, Calendar givenDate) {
	    Calendar resultCalendar = (Calendar)referenceDate.clone();
	    resultCalendar.set(Calendar.YEAR, givenDate.get(Calendar.YEAR));

        // compute and subtract difference between given date and period beginning
	    long refYearsSinceEpoch = referenceDate.get(Calendar.YEAR);
	    long givenYearsSinceEpoch = givenDate.get(Calendar.YEAR);

	    int yearsDiff = (int)moduloWithNegatives((givenYearsSinceEpoch - refYearsSinceEpoch), duration);
	    resultCalendar.add(Calendar.YEAR, -yearsDiff);

	    // if computed period beginning is still after given date, take the previous one
	    if (resultCalendar.after(givenDate)) {
	        resultCalendar.add(Calendar.YEAR, -duration);
	    }

	    return resultCalendar;
	}

    public Calendar findNextPeriodBeginning(Calendar givenDate) {
        Calendar resultCalendar = (Calendar)givenDate.clone();

        switch (unit) {

            case MILLISECOND:
                resultCalendar.add(Calendar.MILLISECOND, duration);
                break;

            case MINUTE:
                resultCalendar.add(Calendar.MINUTE, duration);
                break;

            case HOUR:
                resultCalendar.add(Calendar.HOUR_OF_DAY, duration);
                break;

            case DAY:
                resultCalendar.add(Calendar.DAY_OF_MONTH, duration);
                break;

            case MONTH:
                resultCalendar.add(Calendar.MONTH, duration);
                break;

            case YEAR:
                resultCalendar.add(Calendar.YEAR, duration);
                break;

            default:
            	throw new UnsupportedOperationException("Unit support not implemented.");
        }

        return resultCalendar;
    }

    private long moduloWithNegatives(long divident, long divisor) {
        long result = divident % divisor;
        return result >= 0 ? result : result + divisor;
    }

}
