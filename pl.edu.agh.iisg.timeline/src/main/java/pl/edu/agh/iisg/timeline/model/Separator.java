package pl.edu.agh.iisg.timeline.model;

import java.util.Calendar;

public class Separator {

    private final Calendar date;

    private final int axis;

    public Separator(Calendar date, int axis) {
        this.date = date;
        this.axis = axis;
    }

    public Calendar getDate() {
        return date;
    }

    public int getAxis() {
        return axis;
    }
}
