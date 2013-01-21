package pl.edu.agh.iisg.timeline.view.figure;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

import pl.edu.agh.iisg.timeline.VisualConstants;

public class ElementFigure extends Figure {

    private int titleHeight;

    private int descriptionHeight;

    public ElementFigure(String title, String description) {
        init();
        initHeights(title, description);

        addSquarePoint();
        addTitle(title);
        addDescriptionIfNotEmpty(description);
    }

    private void init() {
        setLayoutManager(new XYLayout());
    }

    private void initHeights(String title, String description) {
        titleHeight = titleHeight(title);
        descriptionHeight = descriptionHeight(description);
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
        int margin = VisualConstants.ELEMENT_LABEL_MARGIN;
        title.setBorder(new MarginBorder(0, margin, 0, margin));
        title.setLayoutManager(new BorderLayout());
        return title;
    }

    private Label createTitleLabel(String text) {
        Label label = new Label(text);
        label.setForegroundColor(VisualConstants.ELEMENT_TITLE_LABEL_COLOR);
        label.setFont(VisualConstants.ELEMENT_TITLE_FONT);
        return label;
    }

    private void addTitleFigure(RectangleFigure title) {
        add(title);
        int offsetX = VisualConstants.ELEMENT_SQUARE_SIZE + VisualConstants.ELEMENT_SQUARE_MARGIN;
        int offsetY = VisualConstants.ELEMENT_SQUARE_MARGIN;
        int width = VisualConstants.AXIS_WIDTH - offsetX;
        setConstraint(title, new Rectangle(offsetX, offsetY, width, titleHeight));
    }

    private void addDescriptionIfNotEmpty(String text) {
        if (text != null && !text.isEmpty()) {
            addDescription(text);
        }
    }

    private void addDescription(String text) {
        RectangleFigure description = createDescriptionBackground();
        Label label = createDescriptionLabel(text);

        description.add(label, BorderLayout.LEFT);
        addDescriptionFigure(description);
    }

    private RectangleFigure createDescriptionBackground() {
        RectangleFigure desc = new RectangleFigure();
        desc.setBackgroundColor(VisualConstants.BACKGROUND);
        desc.setForegroundColor(VisualConstants.ELEMENT_BACKGROUND);
        int margin = VisualConstants.ELEMENT_LABEL_MARGIN;
        desc.setBorder(new MarginBorder(0, margin, 0, margin));
        desc.setLayoutManager(new BorderLayout());
        return desc;
    }

    private Label createDescriptionLabel(String text) {
        Label label = new Label(text);
        label.setForegroundColor(VisualConstants.ELEMENT_DESCRIPTION_LABEL_COLOR);
        label.setFont(VisualConstants.ELEMENT_DESCRIPTION_FONT);
        return label;
    }

    private void addDescriptionFigure(RectangleFigure description) {
        add(description);
        int offsetX = VisualConstants.ELEMENT_SQUARE_SIZE + VisualConstants.ELEMENT_SQUARE_MARGIN;
        int offsetY = VisualConstants.ELEMENT_SQUARE_MARGIN + titleHeight;
        int width = VisualConstants.AXIS_WIDTH - offsetX;
        setConstraint(description, new Rectangle(offsetX, offsetY, width, descriptionHeight));
    }

    /**
     * XXX [leszko] to be changed and put somewhere else.
     *
     * @param name
     * @return
     */
    private int titleHeight(String name) {
        return 15;
    }

    /**
     * XXX [leszko] to be changed and put somewhere else.
     *
     * @param name
     * @return
     */
    private int descriptionHeight(String name) {
        return 16;
    }
}
