package pl.edu.agh.iisg.timeline.view.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

public final class FigureConstants {

    public static final int AXIS_WIDTH = 335;

    public static final int AXIS_HEIGHT = 80;

    public static final int AXIS_GAP = 30;

    public static final int AXIS_BOTTOM_MARGIN = 30;

    public static final Color AXIS_BACKGROUND = new Color(null, 194, 193, 193);

    public static final Color AXIS_FOREGROUND = ColorConstants.black;

    public static final Font AXIS_FONT = new Font(Display.getDefault(), "Franklin Gothic Demi", 13, SWT.NONE);

    public static final int HEADER_HEIGHT = AXIS_HEIGHT + AXIS_BOTTOM_MARGIN;

}
