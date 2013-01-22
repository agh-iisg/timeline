package pl.edu.agh.iisg.timeline.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TimelineDateConverter {

	private static final long DAY_INTERVAL = 1000 * 24 * 60 * 60;
	private static final long MONTH_INTERVAL = 30 * DAY_INTERVAL;
	private static final long YEAR_INTERVAL = 365 * DAY_INTERVAL;

	private static final String DATE_LOCALE = "pl"; // TODO extract to messages
													// properties to ensure
													// locale-specific format

	private static final String YEAR_PATTERN = "yyyy";
	private static final String MONTH_PATTERN = "MMMM ";
	private static final String DAY_PATTERN = "EEEE, d ";
	private static final String HOUR_PATTERN = " 'godz.' hh:mm";

	private long interval;

	public TimelineDateConverter(long interval) {
		this.interval = interval;
	}

	public String getAsString(long date) {
		Locale locale = new Locale(DATE_LOCALE);

		// full pattern: "EEEE, d MMM yyyy 'godz.' hh:mm";
		StringBuilder pattern = new StringBuilder(YEAR_PATTERN);

		if (interval < YEAR_INTERVAL) {
			pattern.insert(0, MONTH_PATTERN);

			if (interval < MONTH_INTERVAL) {
				pattern.deleteCharAt(0);
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
