package pl.edu.agh.iisg.timeline.model;

public class Separator {

    private final long date;

    private final int axis;

    public Separator(long date, int axis) {
        this.date = date;
        this.axis = axis;
    }

    public long getDate() {
        return date;
    }

    public int getAxis() {
        return axis;
    }
}
