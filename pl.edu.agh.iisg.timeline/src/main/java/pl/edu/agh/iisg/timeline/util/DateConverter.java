package pl.edu.agh.iisg.timeline.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateConverter {

	String[] months = { Messages.DateConverter_January,
			Messages.DateConverter_February, Messages.DateConverter_March,
			Messages.DateConverter_April, Messages.DateConverter_May,
			Messages.DateConverter_June, Messages.DateConverter_July,
			Messages.DateConverter_August, Messages.DateConverter_September,
			Messages.DateConverter_October, Messages.DateConverter_November,
			Messages.DateConverter_December };

	private static final long DAY_INTERVAL = 1000 * 24 * 60 * 60;
	private static final long MONTH_INTERVAL = 30 * DAY_INTERVAL;
	private static final long YEAR_INTERVAL = 365 * DAY_INTERVAL;

	private static final String DATE_LOCALE = Messages.DateConverter_Locale;

	private static final String YEAR_PATTERN = "yyyy";
	private static final String MONTH_PATTERN = "MMMM ";
	private static final String DAY_PATTERN = "EEEE, d ";
	private static final String HOUR_PATTERN = " 'godz.' hh:mm";

	private long interval;

	public DateConverter(long interval) {
		this.interval = interval;
	}

	public String asString(long date) {
		Locale locale = new Locale(DATE_LOCALE);

		// full pattern: "EEEE, d MMMM yyyy 'godz.' hh:mm";
		StringBuilder pattern = new StringBuilder(YEAR_PATTERN);

		if (interval < YEAR_INTERVAL) {
			pattern.insert(0, MONTH_PATTERN);

			if (interval < MONTH_INTERVAL) {
				pattern.delete(0, MONTH_PATTERN.length());
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(date);
				int monthNumber = cal.get(Calendar.MONTH);
				pattern.insert(0, "'"  + months[monthNumber] + "' ");

				pattern.insert(0, DAY_PATTERN);

				if (interval < DAY_INTERVAL) {
					pattern.append(HOUR_PATTERN);
				}
			}
		}

		SimpleDateFormat formatter = new SimpleDateFormat(pattern.toString(),
				locale);

		return upperCaseFirstLetter(formatter.format(date));
	}

	private String upperCaseFirstLetter(String strToUpper) {
		char[] charArray = strToUpper.toCharArray();
		charArray[0] = Character.toUpperCase(charArray[0]);
		return new String(charArray);
	}
}
