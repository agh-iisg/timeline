package pl.edu.agh.iisg.timeline.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.editparts.SimpleRootEditPart;

import pl.edu.agh.iisg.timeline.view.ElementsLayer;
import pl.edu.agh.iisg.timeline.view.TimelineScrollPane;

public class TimelineRootEditPart extends SimpleRootEditPart {

    private ScrollPane axesScroll;

    private Layer axesLayer;

    private ScrollPane elementsScroll;

    private Layer elementsLayer;

    private Layer separatorsLayer;

    @Override
    protected IFigure createFigure() {
        Figure root = createRoot();

        createAxesLayer(root);

        Layer scrollLayeredPane = createScrollLayeredPane(root);
        createElementsLayer(scrollLayeredPane);
        createSeparatorsLayer(scrollLayeredPane);

        return root;
    }

    private static Figure createRoot() {
        Figure root = new LayeredPane();
        XYLayout layout = new XYLayout();
        root.setLayoutManager(layout);
        return root;
    }

    private void createAxesLayer(Figure root) {
        axesLayer = new Layer();
        axesLayer.setLayoutManager(new FlowLayout());
        axesScroll = new ScrollPane();
        axesScroll.setScrollBarVisibility(ScrollPane.NEVER);
        axesScroll.setContents(axesLayer);
        root.add(axesScroll);
        root.setConstraint(axesScroll, root.getBounds());
    }

    private Layer createScrollLayeredPane(Figure root) {
        Layer scrollLayeredPane = new LayeredPane();
        scrollLayeredPane.setBorder(new MarginBorder(70, 5, 0, 0));
        elementsScroll = createElementsScroll(scrollLayeredPane);
        root.add(elementsScroll, "Events");
        root.setConstraint(elementsScroll, root.getBounds());
        return scrollLayeredPane;
    }

    private ScrollPane createElementsScroll(Layer layer) {
        TimelineScrollPane scrollPane = new TimelineScrollPane(layer);
        addScrollListeners(scrollPane);
        return scrollPane;
    }

    private void addScrollListeners(TimelineScrollPane scrollPane) {
        scrollPane.getViewport().getVerticalRangeModel().addPropertyChangeListener(new PropertyChangeListener() {
            private static final String SCROLL_PROPERTY_NAME = "value";

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(SCROLL_PROPERTY_NAME)) {
                    notifyScroll((int)evt.getNewValue());
                }
            }
        });

        scrollPane.getViewport().getHorizontalRangeModel().addPropertyChangeListener(new PropertyChangeListener() {

            private static final String SCROLL_PROPERTY_NAME = "value";

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(SCROLL_PROPERTY_NAME)) {
                    axesScroll.scrollHorizontalTo((int)evt.getNewValue());
                }
            }
        });
    }

    private void createElementsLayer(Layer layer) {
        elementsLayer = new ElementsLayer();
        layer.add(elementsLayer);
    }

    private void createSeparatorsLayer(Layer layer) {
        separatorsLayer = new Layer();
        separatorsLayer.setLayoutManager(new XYLayout());
        layer.add(separatorsLayer);
    }

    private void notifyScroll(int position) {
        for (Object child : this.getChildren()) {
            if (child instanceof TimelineDiagramEditPart) {
                ((TimelineDiagramEditPart)child).notifyScroll(position);
            }
        }
    }

    public Layer getAxesLayer() {
        return axesLayer;
    }

    public Layer getEventsLayer() {
        return elementsLayer;
    }

    public Layer getSeparatorsLayer() {
        return separatorsLayer;
    }

    public void scrollVerticalTo(int count) {
        int SPEED = 20;
        int current = elementsScroll.getViewport().getVerticalRangeModel().getValue();
        elementsScroll.scrollVerticalTo(current - SPEED * count);
    }
}
