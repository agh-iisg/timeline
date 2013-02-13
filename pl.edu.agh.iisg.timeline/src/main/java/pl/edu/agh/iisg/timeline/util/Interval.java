package pl.edu.agh.iisg.timeline.util;


public class Interval {

    public enum Unit {
        MILLISECOND, SECOND, MINUTE, HOUR, DAY, MONTH, YEAR;
    }

    private int duration;

    private Unit unit;

    /**
     * Default constructor.
     *
     * @param duration
     *            duration of the interval
     * @param units
     *            units in which interval is expressed.
     */
    public Interval(int duration, Unit units) {
        this.duration = duration;
        this.unit = units;
    }

    /**
     * Get duration of the interval.
     *
     * @return duration.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Gets unit of the interval. See {@link Unit}.
     *
     * @return unit value.
     */
    public Unit getUnit() {
        return unit;
    }

}
