package pl.edu.agh.iisg.timeline.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;

import com.google.common.base.Preconditions;
import com.google.common.collect.TreeMultimap;

public class TimelineDiagram {

    private List<Axis> axes;

    private TreeMultimap<Long, Element> elements;

    public TimelineDiagram() {
        init();
    }

    private void init() {
        axes = new LinkedList<>();
        elements = TreeMultimap.create();
    }

    public boolean addAxis(Axis axis) {
        Preconditions.checkNotNull(axis);
        if (!axes.contains(axis)) {
            return axes.add(axis);
        }
        return false;
    }

    public boolean addElement(Element element) {
        Preconditions.checkNotNull(element);
        return elements.put(element.getDate(), element);
    }

    public void removeElement(Element element) {
        elements.remove(element.getDate(), element);
    }

    public SortedMap<Long, Collection<Element>> getElementsInRange(Long start, Long end) {
        return elements.asMap().subMap(start, end);
    }

    public Collection<Element> getElements() {
        return Collections.unmodifiableCollection(elements.values());
    }

    public boolean removeAxis(Axis axis) {
        Preconditions.checkNotNull(axis);
        return axes.remove(axis);
    }

    public List<Axis> getAxes() {
        return Collections.unmodifiableList(axes);
    }
}
