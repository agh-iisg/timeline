package pl.edu.agh.iisg.timeline.util;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import pl.edu.agh.iisg.timeline.VisualConstants;
import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.Element;
import pl.edu.agh.iisg.timeline.model.Separator;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

/**
 * Positioner for the discrete timeline. It positions the elements in the groups representing the same time on the axis.
 *
 * @author AGH CAST Team
 *
 */
public class DiscretePositioner implements IPositioner {

    public static final int SEPARATOR_GAP = VisualConstants.SEPARATOR_MARGIN_TOP_BOTTOM + VisualConstants.SEPARATOR_HEIGHT;

    public static final Calendar DEFAULT_REFERENCE_DATE = new GregorianCalendar(1970, Calendar.JANUARY, 1);

    private PeriodFinder periodFinder;

    private Calendar referenceDate;

    private final IElementMeasurer measurer;

    private final ISeparatorFactory separatorFactory;

    private TreeMap<Integer, Separator> separators = new TreeMap<>();

    private Map<Calendar, Integer> separatorPosition = new HashMap<>();

    private Map<Element, Integer> positions = new HashMap<>();

    private TreeMultimap<Integer, Element> elementsByPosition = TreeMultimap.create();

    private int maxPosition;

    /**
     * Instantiates a new discrete positioner.
     *
     * @param interval interval to express duration of periods
     * @param measurer the measurer to find elements dimensions.
     * @param separatorFactory the separator factory
     */
    public DiscretePositioner(Interval interval, IElementMeasurer measurer, ISeparatorFactory separatorFactory) {
        this(interval, measurer, separatorFactory, null);
    }

    /**
     * Instantiates a new discrete positioner.
     *
     * @param interval interval to express duration of periods
     * @param measurer the measurer to find elements dimensions.
     * @param separatorFactory the separator factory
     * @param referenceDate the reference date to set period reference point in time. If null then default 1 Jan 1970 00:00:00 is used.
     */
    public DiscretePositioner(Interval interval, IElementMeasurer measurer, ISeparatorFactory separatorFactory, Calendar referenceDate) {
        this.measurer = measurer;
        this.separatorFactory = separatorFactory;

        if (referenceDate != null) {
        	this.referenceDate = referenceDate;
        } else {
        	this.referenceDate = DEFAULT_REFERENCE_DATE;
        }

        periodFinder = new PeriodFinder(interval);
    }

    @Override
    public void init(Collection<Element> elements) {
        ListMultimap<Calendar, Element> elementsByPeriod = groupElementsByPeriod(elements);
        calculateElementsPositions(elementsByPeriod);
    }

    /**
     * Groups elements by period and returns multimap that maps period beginning object to elements.
     *
     * @param elements
     *            elements to map
     * @return {@link ListMultimap} with mapping period beginnings to elements.
     */
    private ListMultimap<Calendar, Element> groupElementsByPeriod(Collection<Element> elements) {
        ListMultimap<Calendar, Element> resultMap = LinkedListMultimap.create();

        Calendar periodBeginning = null;
        for (Element element : elements) {
            Calendar givenDate = new GregorianCalendar();
            givenDate.setTimeInMillis(element.getDate());

            periodBeginning = periodFinder.findPeriodBeginning(referenceDate, givenDate);
            resultMap.put(periodBeginning, element);
        }

        return resultMap;
    }

    /**
     * Calculates elements positions on the diagram.
     *
     * @param elementsByPeriod
     *            {@link Multimap} that holds mapping between period beginnings and elements.
     */
    private void calculateElementsPositions(Multimap<Calendar, Element> elementsByPeriod) {
        Map<Axis, Integer> pos = new HashMap<>();

        int posMax = 0;
        for (Entry<Calendar, Collection<Element>> entry : elementsByPeriod.asMap().entrySet()) {
            Calendar period = entry.getKey();
            Collection<Element> elements = entry.getValue();

            addSeparator(posMax, period);
            posMax += SEPARATOR_GAP;

            for (Element element : elements) {
                Axis axis = element.getAxis();
                Integer posForAxis = pos.get(axis);
                if (posForAxis == null || posForAxis < posMax) {
                    posForAxis = posMax;
                }

                positions.put(element, posForAxis);
                elementsByPosition.put(posForAxis, element);
                int spaceForElement = measurer.getHeightOf(element) + VisualConstants.ELEMENT_MARGIN
                        + VisualConstants.ELEMENT_SQUARE_MARGIN;
                posForAxis += spaceForElement;
                pos.put(axis, posForAxis);
            }
            posMax = Collections.max(pos.values());
        }
        maxPosition = posMax;

    }

    private void addSeparator(int position, Calendar date) {
        int sepPos = position;
        separators.put(sepPos, separatorFactory.newSeparator(date));
        separatorPosition.put(date, sepPos);
    }

    public void setInterval(Interval interval) {
        periodFinder = new PeriodFinder(interval);
    }

    public Interval getInterval() {
    	if (periodFinder != null) {
    		return periodFinder.getInterval();
    	}

    	return null;
    }

    public void setReferenceDate(Calendar referenceDate) {
        this.referenceDate = referenceDate;
    }

    @Override
    public int getPositionOf(Element element) {
        return positions.get(element);
    }

    @Override
    public Collection<Separator> getSeparatorsByPosition(int start, int end) {
        return separators.subMap(start, end).values();
    }

    @Override
    public int getPositionOfSeparator(Separator separator) {
        return separatorPosition.get(separator.getDate());
    }

    @Override
    public Collection<Element> getElementsByPosition(int start, int end) {
        return flatten(elementsByPosition.asMap().subMap(start, end).values());
    }

    private Collection<Element> flatten(Collection<Collection<Element>> values) {
        List<Element> result = new LinkedList<>();
        for (Collection<Element> col : values) {
            result.addAll(col);
        }
        return result;
    }

    @Override
    public int getMaxPosition() {
        return maxPosition;
    }
}
