package pl.edu.agh.iisg.timeline.editpart.dynamic;

import java.util.HashSet;
import java.util.Set;

import pl.edu.agh.iisg.timeline.model.AxisElement;
import pl.edu.agh.iisg.timeline.model.Separator;
import pl.edu.agh.iisg.timeline.positioner.IPositioner;

final public class DynamicModelRefresher implements IModelRefresher {

    private final IPositioner positioner;

    private final IRangeControl range;

    private Set<AxisElement> elements = new HashSet<>();

    private Set<Separator> separators = new HashSet<>();

    public DynamicModelRefresher(IPositioner positioner, IRangeControl rangeRefresher) {
        this.positioner = positioner;
        this.range = rangeRefresher;
    }

    @Override
    public ModelRefresh refresh(int scrollPos) {
        range.setScrollPosition(scrollPos);
        if (range.isChanged()) {
            return refreshRangeChanged();
        } else {
            return ModelRefresh.emptyModelRefresh();
        }
    }

    private ModelRefresh refreshRangeChanged() {
        int start = range.getStart();
        int end = range.getEnd();
        Set<AxisElement> elementsInRange = new HashSet<>(positioner.getElementsByPosition(start, end));
        Set<Separator> separatorsInRange = new HashSet<>(positioner.getSeparatorsByPosition(start, end));

        Set<AxisElement> elementsToAdd = determineToAdd(elements, elementsInRange);
        Set<AxisElement> elementsToRemove = determineToRemove(elements, elementsInRange);
        Set<Separator> separtorsToAdd = determineToAdd(separators, separatorsInRange);
        Set<Separator> separatorsToRemove = determineToRemove(separators, separatorsInRange);

        elements = elementsInRange;
        separators = separatorsInRange;

        return new ModelRefresh(elementsToAdd, elementsToRemove, separtorsToAdd, separatorsToRemove);
    }

    private <T> Set<T> determineToAdd(Set<T> oldSet, Set<T> newSet) {
        HashSet<T> result = new HashSet<>(newSet);
        result.removeAll(oldSet);
        return result;
    }

    private <T> Set<T> determineToRemove(Set<T> oldSet, Set<T> newSet) {
        HashSet<T> result = new HashSet<>(oldSet);
        result.removeAll(newSet);
        return result;
    }
}
