package pl.edu.agh.iisg.timeline.util;

import pl.edu.agh.iisg.timeline.VisualConstants;
import pl.edu.agh.iisg.timeline.model.AxisElement;

public class ElementMeasurer implements IElementMeasurer {

    private ILineSplitter titleSplitter = new LineSplitter(VisualConstants.ELEMENT_TITLE_CHARS_PER_LINE);

    private ILineSplitter descSplitter = new LineSplitter(VisualConstants.ELEMENT_DESC_CHARS_PER_LINE);

    private static final ElementMeasurer INSTANCE = new ElementMeasurer();

    private ElementMeasurer() {
    }

    public static IElementMeasurer getInstance() {
        return INSTANCE;
    }

    @Override
    public int getHeightOf(AxisElement element) {
        String title = element.getName();
        String description = element.getDescription();
        return getHeightOfTitle(title) + getHeightOfDescription(description);
    }

    @Override
    public int getHeightOfTitle(String title) {
        int lines = titleSplitter.split(title).length;
        int up = VisualConstants.ELEMENT_TITLE_LABEL_MARGIN_UP;
        int lineHeight = VisualConstants.ELEMENT_TITLE_LABEL_LINE_HEIGHT;
        return up + (lines * lineHeight);
    }

    @Override
    public int getHeightOfDescription(String desc) {
        int lines = descSplitter.split(desc).length;
        int down = VisualConstants.ELEMENT_DESC_LABEL_MARGIN_DOWN;
        int lineHeight = VisualConstants.ELEMENT_DESC_LABEL_LINE_HEIGHT;
        return (lines * lineHeight) + down;
    }
}
