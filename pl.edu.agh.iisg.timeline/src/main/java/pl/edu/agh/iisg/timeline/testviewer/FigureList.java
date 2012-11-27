package pl.edu.agh.iisg.timeline.testviewer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.Figure;

public class FigureList {

	private final Map<Integer, Figure> figures = new HashMap<>();

	private int start = 0;
	private int end = 0;

	public void addBefore(List<Figure> elements) {
		int startIndex = start - elements.size();
		addRange(elements, startIndex);
		start = startIndex;
	}

	public void addAfter(List<Figure> elements) {
		addRange(elements, end);
		end = end + elements.size();
	}

	public List<Figure> removeBefore(int startIndex) {
		List<Figure> res = new LinkedList<>();
		while (start < startIndex) {
			res.add(figures.remove(start));
			start++;
		}
		return res;
	}

	public List<Figure> removeAfter(int endIndex) {
		List<Figure> res = new LinkedList<>();
		while (end > endIndex) {
			res.add(figures.remove(end));
			end++;
		}
		return res;
	}

	private void addRange(List<Figure> elements, int startIndex) {
		int i = startIndex;
		for (Figure element : elements) {
			figures.put(i, element);
			i++;
		}
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}
}
