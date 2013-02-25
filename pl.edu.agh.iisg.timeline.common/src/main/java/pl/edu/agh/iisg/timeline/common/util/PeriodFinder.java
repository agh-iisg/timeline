package pl.edu.agh.iisg.timeline.common.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PeriodFinder {

    private static final int MONTHS_IN_YEAR = 12;

    private static final int MILLIS_IN_SECOND = 1000;

    private static final int MILLIS_IN_MINUTE = MILLIS_IN_SECOND * 60;

    private static final int MILLIS_IN_HOUR = MILLIS_IN_MINUTE * 60;

    private static final int MILLIS_IN_DAY = MILLIS_IN_HOUR * 24;

	private final Interval interval;

	/**
	 * Default constructor.
	 * @param interval interval to operate on
	 */
	public PeriodFinder(Interval interval) {
		this.interval = interval;
	}

	/**
	 * @return interval that class operates on
	 */
	public Interval getInterval() {
		return interval;
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

        switch (interval.getUnit()) {
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
        long diff = moduloWithNegatives((givenMillis - referenceMillis), interval.getDuration());
        diff = diff >= 0 ? diff : interval.getDuration() + diff;

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

        int secondsDiff = (int)moduloWithNegatives((givenSecondsSinceEpoch - refSecondsSinceEpoch), interval.getDuration());
        resultCalendar.add(Calendar.SECOND, -secondsDiff);

        // if computed period beginning is still after given date, take the previous one
        if (resultCalendar.after(givenDate)) {
        	resultCalendar.add(Calendar.SECOND, -interval.getDuration());
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

        int minutesDiff = (int)moduloWithNegatives((givenMinutesSinceEpoch - refMinutesSinceEpoch), interval.getDuration());
        resultCalendar.add(Calendar.MINUTE, -minutesDiff);

        // if computed period beginning is still after given date, take the previous one
        if (resultCalendar.after(givenDate)) {
            resultCalendar.add(Calendar.MINUTE, -interval.getDuration());
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

        int hoursDiff = (int)moduloWithNegatives((givenHoursSinceEpoch - refHoursSinceEpoch), interval.getDuration());
        resultCalendar.add(Calendar.HOUR_OF_DAY, -hoursDiff);

        // if computed period beginning is still after given date, take the previous one
        if (resultCalendar.after(givenDate)) {
            resultCalendar.add(Calendar.HOUR_OF_DAY, -interval.getDuration());
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

        int daysDiff = (int)moduloWithNegatives((givenDaysSinceEpoch - refDaysSinceEpoch), interval.getDuration());
        resultCalendar.add(Calendar.DAY_OF_MONTH, -daysDiff);

        // if computed period beginning is still after given date, take the previous one
        if (resultCalendar.after(givenDate)) {
            resultCalendar.add(Calendar.DAY_OF_YEAR, -interval.getDuration());
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

         int monthDiff = (int)moduloWithNegatives((givenMonthsSinceEpoch - refMonthsSinceEpoch), interval.getDuration());
         resultCalendar.add(Calendar.MONTH, -monthDiff);

         // if computed period beginning is still after given date, take the previous one
         if (resultCalendar.after(givenDate)) {
             resultCalendar.add(Calendar.MONTH, -interval.getDuration());
         }

         return resultCalendar;
    }

	private Calendar findPeriodBeginningForYears(Calendar referenceDate, Calendar givenDate) {
	    Calendar resultCalendar = (Calendar)referenceDate.clone();
	    resultCalendar.set(Calendar.YEAR, givenDate.get(Calendar.YEAR));

        // compute and subtract difference between given date and period beginning
	    long refYearsSinceEpoch = referenceDate.get(Calendar.YEAR);
	    long givenYearsSinceEpoch = givenDate.get(Calendar.YEAR);

	    int yearsDiff = (int)moduloWithNegatives((givenYearsSinceEpoch - refYearsSinceEpoch), interval.getDuration());
	    resultCalendar.add(Calendar.YEAR, -yearsDiff);

	    // if computed period beginning is still after given date, take the previous one
	    if (resultCalendar.after(givenDate)) {
	        resultCalendar.add(Calendar.YEAR, -interval.getDuration());
	    }

	    return resultCalendar;
	}

    public Calendar findNextPeriodBeginning(Calendar givenDate) {
        Calendar resultCalendar = (Calendar)givenDate.clone();

        switch (interval.getUnit()) {

            case MILLISECOND:
                resultCalendar.add(Calendar.MILLISECOND, interval.getDuration());
                break;

            case MINUTE:
                resultCalendar.add(Calendar.MINUTE, interval.getDuration());
                break;

            case HOUR:
                resultCalendar.add(Calendar.HOUR_OF_DAY, interval.getDuration());
                break;

            case DAY:
                resultCalendar.add(Calendar.DAY_OF_MONTH, interval.getDuration());
                break;

            case MONTH:
                resultCalendar.add(Calendar.MONTH, interval.getDuration());
                break;

            case YEAR:
                resultCalendar.add(Calendar.YEAR, interval.getDuration());
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
