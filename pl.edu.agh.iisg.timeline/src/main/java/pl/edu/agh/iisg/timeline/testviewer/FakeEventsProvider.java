package pl.edu.agh.iisg.timeline.testviewer;

import java.util.LinkedList;
import java.util.List;

public class FakeEventsProvider implements IEventsProvider {

	@Override
	public List<Event> provideEvents(int from, int to) {
		List<Event> res = new LinkedList<Event>();
		for (int i = from; i < to; i++) {
			res.add(new Event("Firma", "index: " + String.valueOf(i)));
		}
		return res;
	}

}
