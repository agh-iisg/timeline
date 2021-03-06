package pl.edu.agh.iisg.timeline.util;

import java.util.Calendar;

import pl.edu.agh.iisg.timeline.model.Separator;

public class SeparatorFactory implements ISeparatorFactory {

    private final int axis;

    public SeparatorFactory(int axis) {
        this.axis = axis;
    }

    @Override
    public Separator newSeparator(Calendar date) {
        return new Separator(date, axis);
    }
}
