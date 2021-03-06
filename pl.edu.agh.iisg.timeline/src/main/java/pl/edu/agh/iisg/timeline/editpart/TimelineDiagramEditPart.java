package pl.edu.agh.iisg.timeline.editpart;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import pl.edu.agh.iisg.timeline.editpart.dynamic.DefaultRangeControl;
import pl.edu.agh.iisg.timeline.editpart.dynamic.DynamicModelRefresher;
import pl.edu.agh.iisg.timeline.editpart.dynamic.IModelRefresher;
import pl.edu.agh.iisg.timeline.editpart.dynamic.ModelRefresh;
import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.Element;
import pl.edu.agh.iisg.timeline.model.Separator;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;
import pl.edu.agh.iisg.timeline.util.DateConverter;
import pl.edu.agh.iisg.timeline.util.DiscretePositioner;
import pl.edu.agh.iisg.timeline.util.ElementMeasurer;
import pl.edu.agh.iisg.timeline.util.IElementMeasurer;
import pl.edu.agh.iisg.timeline.util.IPositioner;
import pl.edu.agh.iisg.timeline.util.ISeparatorFactory;
import pl.edu.agh.iisg.timeline.util.Interval;
import pl.edu.agh.iisg.timeline.util.SeparatorFactory;
import pl.edu.agh.iisg.timeline.view.figure.ElementsForAxisLayer;

public class TimelineDiagramEditPart extends AbstractGraphicalEditPart {

    private IPositioner positioner;

    private IModelRefresher refresher;

    private DateConverter converter;

    private Map<Axis, IFigure> axisLayers = new HashMap<>();

    private int currectScrollPosition = 0;

    public TimelineDiagramEditPart(TimelineDiagram model) {
        super.setModel(model);
        init();
        initElementPositions();
    }

    private void init() {
        Interval interval = new Interval(1, Interval.Unit.DAY);

        IElementMeasurer measurer = ElementMeasurer.getInstance();
        int axis = ((TimelineDiagram)getModel()).getAxes().size();
        ISeparatorFactory separatorFactory = new SeparatorFactory(axis);

        positioner = new DiscretePositioner(interval, measurer, separatorFactory);
        refresher = new DynamicModelRefresher(positioner, new DefaultRangeControl());
        converter = new DateConverter(interval);
    }

    private void initElementPositions() {
        Collection<Element> elements = ((TimelineDiagram)getModel()).getElements();
        positioner.init(elements);
    }

    @Override
    protected IFigure createFigure() {
        return new Figure();
    }

    @Override
    protected void createEditPolicies() {
        // do nothing
    }

    @Override
    protected List<Object> getModelChildren() {
        List<Object> list = new LinkedList<>();
        list.addAll(((TimelineDiagram)getModel()).getAxes());
        return list;
    }

    @Override
    protected void refreshChildren() {
        super.refreshChildren();
        refreshScroll();
    }

    public void notifyScroll(int position) {
        this.currectScrollPosition = position;
        refreshScroll();
    }

    public void notifyScrollEnd() {
        notifyScroll(positioner.getMaxPosition());
    }

    private void refreshScroll() {
        ModelRefresh refresh = refresher.refresh(currectScrollPosition);
        if (refresh.shouldRefresh()) {
            addChildren(refresh.getElementsToAdd());
            removeChildren(refresh.getElementsToRemove());
            addChildren(refresh.getSeparatorsToAdd());
            removeChildren(refresh.getSeparatorsToRemove());
        }
    }

    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        if (childEditPart instanceof AxisEditPart) {
            addAxisChildVisual((AxisEditPart)childEditPart, index);
        } else if (childEditPart instanceof ElementEditPart) {
            addElementChildVisual((ElementEditPart)childEditPart);
        } else if (childEditPart instanceof SeparatorEditPart) {
            addSeparatorChildVisual((SeparatorEditPart)childEditPart);
        }
    }

    @Override
    protected void removeChildVisual(EditPart childEditPart) {
        if (childEditPart instanceof AxisEditPart) {
            removeAxisChildVisual((AxisEditPart)childEditPart);
        } else if (childEditPart instanceof ElementEditPart) {
            removeElementChildVisual((ElementEditPart)childEditPart);
        } else if (childEditPart instanceof SeparatorEditPart) {
            removeSeparatorChildVisual((SeparatorEditPart)childEditPart);
        }
    }

    private void addAxisChildVisual(AxisEditPart childEditPart, int index) {
        IFigure axesLayerChild = childEditPart.getFigure();
        ((TimelineRootEditPart)getRoot()).getAxesLayer().add(axesLayerChild);

        IFigure axisLayer = createXYFigure();
        ((TimelineRootEditPart)getRoot()).getEventsLayer().add(axisLayer);
        axisLayers.put((Axis)childEditPart.getModel(), axisLayer);
    }

    private void removeAxisChildVisual(AxisEditPart childEditPart) {
        IFigure axisLayer = axisLayers.remove(childEditPart.getModel());
        ((TimelineRootEditPart)getRoot()).getEventsLayer().remove(axisLayer);
        ((TimelineRootEditPart)getRoot()).getAxesLayer().remove(childEditPart.getFigure());
    }

    private IFigure createXYFigure() {
        return new ElementsForAxisLayer();
    }

    private void addElementChildVisual(ElementEditPart childEditPart) {
        Element model = (Element)childEditPart.getModel();
        IFigure parent = axisLayers.get(model.getAxis());
        IFigure child = childEditPart.getFigure();
        parent.add(child);
        int y = getPositionOf((Element)childEditPart.getModel());
        parent.setConstraint(child, new Rectangle(0, y, -1, -1));
    }

    private void removeElementChildVisual(ElementEditPart childEditPart) {
        Element model = (Element)childEditPart.getModel();
        IFigure parent = axisLayers.get(model.getAxis());
        IFigure child = childEditPart.getFigure();
        parent.remove(child);
    }

    private void addSeparatorChildVisual(SeparatorEditPart childEditPart) {
        Separator model = (Separator)childEditPart.getModel();
        int position = positioner.getPositionOfSeparator(model);
        Layer layer = ((TimelineRootEditPart)getRoot()).getSeparatorsLayer();
        IFigure figure = childEditPart.getFigure();
        layer.add(figure);
        layer.setConstraint(figure, new Rectangle(0, position, -1, -1));
    }

    private void removeSeparatorChildVisual(SeparatorEditPart childEditPart) {
        Layer layer = ((TimelineRootEditPart)getRoot()).getSeparatorsLayer();
        IFigure figure = childEditPart.getFigure();
        layer.remove(figure);
    }

    private int getPositionOf(Element element) {
        return positioner.getPositionOf(element);
    }

    private <T> void addChildren(Collection<T> models) {
        for (T model : models) {
            EditPart editPart = createChild(model);
            addChild(editPart, 0);
        }
    }

    private <T> void removeChildren(Collection<T> models) {
        for (T model : models) {
            EditPart editPart = (EditPart)getViewer().getEditPartRegistry().get(model);
            removeChild(editPart);
        }
    }

    public DateConverter getConverter() {
        return converter;
    }
}
