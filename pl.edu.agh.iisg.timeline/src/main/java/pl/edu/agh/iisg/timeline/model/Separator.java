package pl.edu.agh.iisg.timeline.model;

public class Separator {

    private final long value;

    private final int axis;

    public Separator(long value, int axis) {
        this.value = value;
        this.axis = axis;
    }

    public long getValue() {
        return value;
    }

    public int getAxis() {
        return axis;
    }
}
