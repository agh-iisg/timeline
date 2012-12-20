package pl.edu.agh.iisg.timeline.editpart.dynamic;

import java.util.Collection;
import java.util.Collections;

import pl.edu.agh.iisg.timeline.model.AxisElement;
import pl.edu.agh.iisg.timeline.model.Separator;

public class ModelRefresh {

	private final Collection<AxisElement> elementsToAdd;
	private final Collection<AxisElement> elementsToRemove;
	private final Collection<Separator> separatorsToAdd;
	private final Collection<Separator> separatorsToRemove;

	public ModelRefresh(Collection<AxisElement> elementsToAdd,
			Collection<AxisElement> elementsToRemove,
			Collection<Separator> separatorsToAdd,
			Collection<Separator> separatorsToRemove) {
		this.elementsToAdd = elementsToAdd;
		this.elementsToRemove = elementsToRemove;
		this.separatorsToAdd = separatorsToAdd;
		this.separatorsToRemove = separatorsToRemove;
	}

	public Collection<AxisElement> getElementsToAdd() {
		return elementsToAdd;
	}

	public Collection<AxisElement> getElementsToRemove() {
		return elementsToRemove;
	}

	public Collection<Separator> getSeparatorsToAdd() {
		return separatorsToAdd;
	}

	public Collection<Separator> getSeparatorsToRemove() {
		return separatorsToRemove;
	}

	public boolean shouldRefresh() {
		return !elementsToAdd.isEmpty() || !elementsToRemove.isEmpty()
				|| !separatorsToAdd.isEmpty() || !separatorsToRemove.isEmpty();
	}

	public static ModelRefresh emptyModelRefresh() {
		return new ModelRefresh(Collections.<AxisElement> emptyList(),
				Collections.<AxisElement> emptyList(),
				Collections.<Separator> emptyList(),
				Collections.<Separator> emptyList());
	}
}
