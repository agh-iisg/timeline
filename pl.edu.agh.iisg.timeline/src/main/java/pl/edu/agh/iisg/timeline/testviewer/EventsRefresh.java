package pl.edu.agh.iisg.timeline.testviewer;

import java.util.Collection;
import java.util.Collections;

public class EventsRefresh {

	private final Collection<AbsoluteEventFigure> eventsToAdd;
	private final Collection<AbsoluteEventFigure> eventsToRemove;

	public EventsRefresh(Collection<AbsoluteEventFigure> eventsToAdd,
			Collection<AbsoluteEventFigure> eventsToRemove) {
		this.eventsToAdd = eventsToAdd;
		this.eventsToRemove = eventsToRemove;
	}

	public Collection<AbsoluteEventFigure> getEventsToAdd() {
		return eventsToAdd;
	}

	public Collection<AbsoluteEventFigure> getEventsToRemove() {
		return eventsToRemove;
	}

	public boolean shouldRefresh() {
		return !(eventsToAdd.isEmpty() && eventsToRemove.isEmpty());
	}

	public static EventsRefresh emptyEventsRefresh() {
		return new EventsRefresh(Collections.<AbsoluteEventFigure> emptyList(),
				Collections.<AbsoluteEventFigure> emptyList());
	}
}
