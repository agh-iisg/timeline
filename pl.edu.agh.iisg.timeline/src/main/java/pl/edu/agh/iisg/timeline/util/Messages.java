package pl.edu.agh.iisg.timeline.util;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "pl.edu.agh.iisg.timeline.util.messages"; //$NON-NLS-1$
	public static String DateConverter_April;
	public static String DateConverter_August;
	public static String DateConverter_December;
	public static String DateConverter_February;
	public static String DateConverter_January;
	public static String DateConverter_July;
	public static String DateConverter_June;
	public static String DateConverter_Locale;
	public static String DateConverter_March;
	public static String DateConverter_May;
	public static String DateConverter_November;
	public static String DateConverter_October;
	public static String DateConverter_September;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
