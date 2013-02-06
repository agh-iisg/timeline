package pl.edu.agh.iisg.timeline.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Interval {

	enum Units {
		MILLISECONDS, MINUTES, HOURS, DAYS, MONTHS, YEAR;
	}

	private Long value;

	private Units units;

	public Interval(long duration, Units units) {
		this.value = duration;
		this.units = units;
	}

	public Long getDuration() {
		return value;
	}

	public Units getUnits() {
		return units;
	}

	public Calendar findPeriodBeginning(Calendar referenceDate, Calendar givenDate) {


		Calendar resultCalendar = new GregorianCalendar();

		switch(units) {

		case MILLISECONDS:

			long referenceMillis = referenceDate.getTimeInMillis();
			long givenMillis = givenDate.getTimeInMillis();

			// Difference between period beginning and given date with modulo accepting negatives.
			long diff = (givenMillis - referenceMillis) % value;
			diff = diff > 0 ? diff : value + diff;

			resultCalendar.setTimeInMillis(givenMillis - diff);
			break;

		case MINUTES:
			break;

		case HOURS:
			break;

		case DAYS:
			break;

		case MONTHS:
			break;

		case YEAR:
			break;

		}


		return resultCalendar;
	}


}
