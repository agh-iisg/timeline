package pl.edu.agh.iisg.timeline.common.figure;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

import pl.edu.agh.iisg.timeline.common.util.ElementMeasurer;
import pl.edu.agh.iisg.timeline.common.util.IElementMeasurer;
import pl.edu.agh.iisg.timeline.common.util.ILineSplitter;
import pl.edu.agh.iisg.timeline.common.util.LineSplitter;

import pl.edu.agh.iisg.timeline.common.VisualConstants;

public class ElementFigure extends Figure {

    private int titleHeight;

    private int descriptionHeight;

    public ElementFigure(String title, String description) {
        init();
        initHeights(title, description);

        addSquarePoint();
        addTitle(title);
        addDescIfNotEmpty(description);
    }

    private void init() {
        setLayoutManager(new XYLayout());
    }

    private void initHeights(String title, String description) {
        IElementMeasurer measurer = ElementMeasurer.getInstance();
        titleHeight = measurer.getHeightOfTitle(title);
        descriptionHeight = measurer.getHeightOfDesc(description);
    }

    private void addSquarePoint() {
        RectangleFigure background = createSquareBackground();
        RectangleFigure square = createSquare();

        background.add(square, BorderLayout.CENTER);
        addSquareBackgroundFigure(background);
    }

    private RectangleFigure createSquareBackground() {
        RectangleFigure background = new RectangleFigure();
        background.setBackgroundColor(VisualConstants.BACKGROUND);
        background.setForegroundColor(VisualConstants.BACKGROUND);
        background.setLayoutManager(new BorderLayout());
        int margin = VisualConstants.ELEMENT_SQUARE_MARGIN;
        background.setBorder(new MarginBorder(margin, 0, margin, margin));
        return background;
    }

    private RectangleFigure createSquare() {
        RectangleFigure square = new RectangleFigure();
        square.setBackgroundColor(VisualConstants.ELEMENT_SQUARE_COLOR);
        square.setForegroundColor(VisualConstants.ELEMENT_SQUARE_COLOR);
        return square;
    }

    private void addSquareBackgroundFigure(RectangleFigure background) {
        int height = VisualConstants.ELEMENT_SQUARE_SIZE + 2 * VisualConstants.ELEMENT_SQUARE_MARGIN;
        int width = VisualConstants.ELEMENT_SQUARE_SIZE + VisualConstants.ELEMENT_SQUARE_MARGIN;
        add(background);
        setConstraint(background, new Rectangle(0, 0, width, height));
    }

    private void addTitle(String text) {
        RectangleFigure title = createTitleBackground();
        Label label = createTitleLabel(text);

        title.add(label, BorderLayout.LEFT);
        addTitleFigure(title);
    }

    private RectangleFigure createTitleBackground() {
        RectangleFigure title = new RectangleFigure();
        title.setBackgroundColor(VisualConstants.ELEMENT_BACKGROUND);
        title.setForegroundColor(VisualConstants.ELEMENT_BACKGROUND);
        int margin = VisualConstants.ELEMENT_LABEL_MARGIN_LEFT;
        title.setBorder(new MarginBorder(0, margin, 0, margin));
        title.setLayoutManager(new BorderLayout());
        return title;
    }

    private Label createTitleLabel(String text) {
        Label label = new Label(titleToManyLines(text));
        label.setForegroundColor(VisualConstants.ELEMENT_TITLE_LABEL_COLOR);
        label.setFont(VisualConstants.ELEMENT_TITLE_FONT);
        return label;
    }

    private String titleToManyLines(String text) {
        ILineSplitter splitter = new LineSplitter(VisualConstants.ELEMENT_TITLE_CHARS_PER_LINE);
        return toManyLines(text, splitter);

    }

    private void addTitleFigure(RectangleFigure title) {
        add(title);
        int offsetX = VisualConstants.ELEMENT_SQUARE_SIZE + VisualConstants.ELEMENT_SQUARE_MARGIN;
        int offsetY = VisualConstants.ELEMENT_SQUARE_MARGIN;
        int width = VisualConstants.AXIS_WIDTH - offsetX;
        setConstraint(title, new Rectangle(offsetX, offsetY, width, titleHeight));
    }

    private void addDescIfNotEmpty(String text) {
        if (text != null && !text.isEmpty()) {
            addDesc(text);
        }
    }

    private void addDesc(String text) {
        RectangleFigure description = createDescBackground();
        Label label = createDescLabel(text);

        description.add(label, BorderLayout.LEFT);
        addDescFigure(description);
    }

    private RectangleFigure createDescBackground() {
        RectangleFigure desc = new RectangleFigure();
        desc.setBackgroundColor(VisualConstants.BACKGROUND);
        desc.setForegroundColor(VisualConstants.ELEMENT_BACKGROUND);
        int marginHorizontal = VisualConstants.ELEMENT_LABEL_MARGIN_LEFT;
        int marginVertical = VisualConstants.ELEMENT_DESC_LABEL_MARGIN;
        desc.setBorder(new MarginBorder(marginVertical, marginHorizontal, marginVertical, marginHorizontal));
        desc.setLayoutManager(new BorderLayout());
        return desc;
    }

    private Label createDescLabel(String text) {
        Label label = new Label(descToManyLines(text));
        label.setForegroundColor(VisualConstants.ELEMENT_DESC_LABEL_COLOR);
        label.setFont(VisualConstants.ELEMENT_DESC_FONT);
        return label;
    }

    private String descToManyLines(String text) {
        ILineSplitter splitter = new LineSplitter(VisualConstants.ELEMENT_DESC_CHARS_PER_LINE);
        return toManyLines(text, splitter);
    }

    private void addDescFigure(RectangleFigure description) {
        add(description);
        int offsetX = VisualConstants.ELEMENT_SQUARE_SIZE + VisualConstants.ELEMENT_SQUARE_MARGIN;
        int offsetY = VisualConstants.ELEMENT_SQUARE_MARGIN + titleHeight;
        int width = VisualConstants.AXIS_WIDTH - offsetX;
        setConstraint(description, new Rectangle(offsetX, offsetY, width, descriptionHeight));
    }

    private String toManyLines(String text, ILineSplitter splitter) {
        String[] lines = splitter.split(text);
        StringBuilder res = new StringBuilder();
        for (String line : lines) {
            res.append(line + "\n"); //$NON-NLS-1$
        }
        return res.toString().trim();
    }
}
