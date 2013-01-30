package pl.edu.agh.iisg.timeline.editpart.dynamic;

import java.util.Collection;
import java.util.Collections;

import pl.edu.agh.iisg.timeline.model.Element;
import pl.edu.agh.iisg.timeline.model.Separator;

public class ModelRefresh {

    private final Collection<Element> elementsToAdd;

    private final Collection<Element> elementsToRemove;

    private final Collection<Separator> separatorsToAdd;

    private final Collection<Separator> separatorsToRemove;

    public ModelRefresh(Collection<Element> elementsToAdd, Collection<Element> elementsToRemove,
            Collection<Separator> separatorsToAdd, Collection<Separator> separatorsToRemove) {
        this.elementsToAdd = elementsToAdd;
        this.elementsToRemove = elementsToRemove;
        this.separatorsToAdd = separatorsToAdd;
        this.separatorsToRemove = separatorsToRemove;
    }

    public Collection<Element> getElementsToAdd() {
        return elementsToAdd;
    }

    public Collection<Element> getElementsToRemove() {
        return elementsToRemove;
    }

    public Collection<Separator> getSeparatorsToAdd() {
        return separatorsToAdd;
    }

    public Collection<Separator> getSeparatorsToRemove() {
        return separatorsToRemove;
    }

    public boolean shouldRefresh() {
        return !elementsToAdd.isEmpty() || !elementsToRemove.isEmpty() || !separatorsToAdd.isEmpty() || !separatorsToRemove.isEmpty();
    }

    public static ModelRefresh emptyModelRefresh() {
        return new ModelRefresh(Collections.<Element> emptyList(), Collections.<Element> emptyList(),
                Collections.<Separator> emptyList(), Collections.<Separator> emptyList());
    }
}
