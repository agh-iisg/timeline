package pl.edu.agh.iisg.timeline.util;

import pl.edu.agh.iisg.timeline.VisualConstants;
import pl.edu.agh.iisg.timeline.model.Element;

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
    public int getHeightOf(Element element) {
        String title = element.getTitle();
        String desc = element.getDescription();
        return getHeightOfTitle(title) + getHeightOfDesc(desc);
    }

    @Override
    public int getHeightOfTitle(String title) {
        int lines = titleSplitter.split(title).length;
        int lineHeight = VisualConstants.ELEMENT_TITLE_LABEL_LINE_HEIGHT;
        return lines * lineHeight;
    }

    @Override
    public int getHeightOfDesc(String desc) {
        int lines = Math.max(1, descSplitter.split(desc).length);
        int marginVertical = VisualConstants.ELEMENT_DESC_LABEL_MARGIN;
        int lineHeight = VisualConstants.ELEMENT_DESC_LABEL_LINE_HEIGHT;
        return (lines * lineHeight) + 2 * marginVertical;
    }
}
