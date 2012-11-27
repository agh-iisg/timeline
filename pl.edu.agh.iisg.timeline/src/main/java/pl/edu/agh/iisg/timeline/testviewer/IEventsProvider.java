package pl.edu.agh.iisg.timeline.testviewer;

import java.util.List;

public interface IEventsProvider {
	List<Event> provideEvents(int from, int to);
}
