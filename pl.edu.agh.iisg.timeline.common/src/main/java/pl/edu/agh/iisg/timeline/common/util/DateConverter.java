package pl.edu.agh.iisg.timeline.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Converter from date to string. The string format depends on the interval (the bigger interval is, the more detailed the date string is).
 *
 * @author AGH CAST Team
 */
public class DateConverter {

    String[] months = { Messages.DateConverter_January, Messages.DateConverter_February, Messages.DateConverter_March,
        Messages.DateConverter_April, Messages.DateConverter_May, Messages.DateConverter_June, Messages.DateConverter_July,
        Messages.DateConverter_August, Messages.DateConverter_September, Messages.DateConverter_October, Messages.DateConverter_November,
        Messages.DateConverter_December };

    private static final String DATE_LOCALE = Messages.DateConverter_Locale;

    private static final String YEAR_PATTERN = "yyyy"; //$NON-NLS-1$

    private static final String MONTH_PATTERN = "MMMM"; //$NON-NLS-1$

    private static final String DAY_PATTERN = "EEEE, d"; //$NON-NLS-1$

    private static final String HOUR_PATTERN = String.format("'%s'HH:mm", Messages.DateConverter_HourPrefix); //$NON-NLS-1$

    private static final String SECOND_PATTERN = ":ss"; //$NON-NLS-1$

    private static final String MILLISECOND_PATTERN = ":SSS"; //$NON-NLS-1$


    private Interval interval;

    public DateConverter(Interval interval) {
        this.interval = interval;
    }

    public String asString(Calendar date) {
        Locale locale = new Locale(DATE_LOCALE);

        // full pattern: "EEEE, d MMMM yyyy 'godz.' hh:mm:ss";
        String pattern;
        int monthNumber = date.get(Calendar.MONTH);

        switch (interval.getUnit()) {
        case YEAR:
        	pattern = String.format("%s", YEAR_PATTERN); //$NON-NLS-1$
        	break;

        case MONTH:
        	pattern = String.format("%s %s", MONTH_PATTERN, YEAR_PATTERN); //$NON-NLS-1$
        	break;

        case DAY:
        	pattern = String.format("%s '%s' %s",
        			DAY_PATTERN, months[monthNumber], YEAR_PATTERN); //$NON-NLS-1$
        	break;

        case HOUR:
        case MINUTE:
        	pattern = String.format("%s '%s' %s %s",
        			DAY_PATTERN, months[monthNumber], YEAR_PATTERN, HOUR_PATTERN); //$NON-NLS-1$
        	break;

        case SECOND:
        default:
        	pattern = String.format("%s '%s' %s %s%s",
        			DAY_PATTERN, months[monthNumber], YEAR_PATTERN, HOUR_PATTERN, SECOND_PATTERN); //$NON-NLS-1$
        	break;

        case MILLISECOND:
        	pattern = String.format("%s '%s' %s %s%s%s",
        			DAY_PATTERN, months[monthNumber], YEAR_PATTERN, HOUR_PATTERN, SECOND_PATTERN, MILLISECOND_PATTERN); //$NON-NLS-1$
        	break;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);

        return upperCaseFirstLetter(formatter.format(date.getTime()));
    }

    private String upperCaseFirstLetter(String strToUpper) {
        char[] charArray = strToUpper.toCharArray();
        charArray[0] = Character.toUpperCase(charArray[0]);
        return new String(charArray);
    }
}
