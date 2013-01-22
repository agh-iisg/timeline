package pl.edu.agh.iisg.timeline;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

public final class VisualConstants {

    public static final Color BACKGROUND = ColorConstants.white;

    public static final int AXIS_WIDTH = 322;

    public static final int AXIS_HEIGHT = 80;

    public static final int AXIS_GAP = 30;

    public static final int AXIS_MARGIN = 7;

    public static final int AXIS_LABEL_MARGIN = 10;

    public static final int AXIS_ICON_WIDTH = 41;

    public static final int AXIS_ICON_HEIGHT = 41;

    public static final int AXIS_LABEL_WIDTH = AXIS_WIDTH - AXIS_ICON_WIDTH - 3 * AXIS_LABEL_MARGIN;

    public static final Color AXIS_BACKGROUND = new Color(null, 194, 193, 193);

    public static final Color AXIS_FONT_COLOR = ColorConstants.black;

    public static final Font AXIS_FONT = new Font(Display.getDefault(), "Franklin Gothic Demi", 9, SWT.NONE);

    public static final int SEPARATOR_HEIGHT = 15;

    public static final int SEPARATOR_MARGIN_TOP_BOTTOM = 5;

    public static final int SEPARATOR_LABEL_MARGIN = 5;

    public static final Color SEPARATOR_BACKGROUND = new Color(null, 150, 174, 190);

    public static final Color SEPARATOR_SPACE_BACKGROUND = new Color(null, 201, 215, 224);

    public static final Color SEPARATOR_FONT_COLOR = ColorConstants.white;

    public static final Font SEPARATOR_FONT = new Font(Display.getDefault(), "Franklin Gothic Demi", 9, SWT.NONE);

    public static final int VERTICAL_LINE_WIDTH = 4;

    public static final Color VERTICAL_LINE_COLOR = new Color(null, 194, 193, 193);

    public static final Color ELEMENT_SQUARE_COLOR = new Color(null, 121, 137, 146);

    public static final int ELEMENT_SQUARE_SIZE = 15;

    public static final int ELEMENT_SQUARE_MARGIN = 4;

    public static final Color ELEMENT_BACKGROUND = new Color(null, 222, 222, 221);

    public static final int ELEMENT_LABEL_MARGIN_LEFT = 9;

    public static final int ELEMENT_TITLE_LABEL_MARGIN_UP = 3;

    public static final int ELEMENT_TITLE_LABEL_LINE_HEIGHT = 13;

    public static final Color ELEMENT_TITLE_LABEL_COLOR = ColorConstants.black;

    public static final Font ELEMENT_TITLE_FONT = new Font(Display.getDefault(), "Franklin Gothic Demi", 9, SWT.NONE);

    public static final int ELEMENT_TITLE_CHARS_PER_LINE = 50;

    public static final int ELEMENT_DESC_LABEL_MARGIN_DOWN = 5;

    public static final int ELEMENT_DESC_LABEL_LINE_HEIGHT = 15;

    public static final Color ELEMENT_DESC_LABEL_COLOR = ColorConstants.black;

    public static final Font ELEMENT_DESC_FONT = new Font(Display.getDefault(), "Franklin Gothic Book", 8, SWT.NONE);

    public static final int ELEMENT_DESC_CHARS_PER_LINE = 10;

    public static final int ELEMENT_MARGIN = 4;
}
