package pl.edu.agh.iisg.timeline.testviewer;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class FakeEventsRefresher implements IEventsRefresher {

	private final List<AbsoluteEventFigure> figures = new LinkedList<>();
	private List<AbsoluteEventFigure> eventsToAdd;

	@Override
	public EventsRefresh refreshEvents(int position) {
		// List<AbsoluteEventFigure> eventsToRemove = prepareFiguresToRemove();
		// eventsToAdd = addRandomFigures();
		// return new EventsRefresh(eventsToAdd, eventsToRemove);
		return new EventsRefresh(createOneEventToAdd(),
				new LinkedList<AbsoluteEventFigure>());
	}

	private Collection<AbsoluteEventFigure> createOneEventToAdd() {
		List<AbsoluteEventFigure> res = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			res.add(createAbsoluteFigureFrom(i * 10000));
		}

		return res;
	}

	private List<AbsoluteEventFigure> prepareFiguresToRemove() {
		List<AbsoluteEventFigure> res = new LinkedList<>();

		Random rand = new Random();
		for (AbsoluteEventFigure figure : figures) {
			if (rand.nextBoolean()) {
				res.add(figure);
			}
		}

		figures.removeAll(res);

		return res;

	}

	private List<AbsoluteEventFigure> addRandomFigures() {
		List<AbsoluteEventFigure> res = new LinkedList<>();
		Random rand = new Random();
		for (int i = 0; i < 1000; i++) {
			if (rand.nextBoolean()) {
				res.add(createAbsoluteFigureFrom(i));
			}
		}

		figures.addAll(res);

		return res;
	}

	private AbsoluteEventFigure createAbsoluteFigureFrom(int index) {
		EventFigure figure = new EventFigure("Company", "nr "
				+ String.valueOf(index));
		Rectangle position = new Rectangle(new Point(0, index * 100 + 50),
				figure.getPreferredSize());
		return new AbsoluteEventFigure(figure, position);
	}
}
