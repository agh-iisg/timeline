package pl.edu.agh.iisg.timeline;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

public final class VisualConstants {

    public static final int AXIS_WIDTH = 322;

    public static final int AXIS_HEIGHT = 80;

    public static final int AXIS_GAP = 30;

    public static final int AXIS_MARGIN = 7;

    public static final int AXIS_LABEL_MARGIN = 10;

    public static final Color AXIS_BACKGROUND = new Color(null, 194, 193, 193);

    public static final Color AXIS_FONT_COLOR = ColorConstants.black;

    public static final Font AXIS_FONT = new Font(Display.getDefault(), "Franklin Gothic Demi", 12, SWT.NONE);

    public static final int SEPARATOR_HEIGHT = 15;

    public static final int SEPARATOR_MARGIN_BOTTOM = 5;

    public static final int SEPARATOR_LABEL_MARGIN = 5;

    public static final Color SEPARATOR_BACKGROUND = new Color(null, 150, 174, 190);

    public static final Color SEPARATOR_SPACE_BACKGROUND = new Color(null, 201, 215, 224);

    public static final Color SEPARATOR_FONT_COLOR = ColorConstants.white;

    public static final Font SEPARATOR_FONT = new Font(Display.getDefault(), "Franklin Gothic Demi", 9, SWT.NONE);

}
