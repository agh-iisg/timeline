package pl.edu.agh.iisg.timeline.testviewer;

import java.util.List;

import org.eclipse.draw2d.Figure;

public class EventsRefresher implements IEventsRefresher {

	private final IEventsProvider provider = new FakeEventsProvider();

	private final FigureList figures = new FigureList();

	private final int buffer = 10;
	private final int inertia = 2;
	private final int viewportSize = 10;

	private final int totalElements = 100;

	@Override
	public EventsRefresh refreshEvents(int viewPosition) {
		if (isSmallEnough(viewPosition)) {
			return addAndRemoveFiguresBefore(viewPosition);
		}
		if (isBigEnough(viewPosition)) {
			return addAndRemoveFiguresBefore(viewPosition);
		} else {
			return EventsRefresh.emptyEventsRefresh();
		}
	}

	private boolean isSmallEnough(int position) {
		return position < figures.getStart() - inertia;
	}

	private boolean isBigEnough(int position) {
		return position + viewportSize > figures.getEnd() + inertia;
	}

	private EventsRefresh addAndRemoveFiguresBefore(int position) {
		// TODO porzadek w tej metodzie
		int start = Math.max(0, position - buffer);
		List<Event> events = provider.provideEvents(start, figures.getStart());
		List<Figure> eventFigures = createFigures(events);
		figures.addBefore(eventFigures);
		List<AbsoluteEventFigure> figuresToAdd = createAbsoluteFigures(eventFigures);

		int end = Math.min(totalElements, position + viewportSize + buffer);
		List<Figure> figuresToRemove = figures.removeAfter(end);

		return null;
	}

	private List<AbsoluteEventFigure> createAbsoluteFigures(
			List<Figure> eventFigures) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Figure> createFigures(List<Event> events) {
		return null;
	}
}
