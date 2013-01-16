package pl.edu.agh.iisg.timeline.model;

public class SeparatorFactory implements ISeparatorFactory {

    private final int axis;

    public SeparatorFactory(int axis) {
        this.axis = axis;
    }

    @Override
    public Separator newSeparator(long value) {
        return new Separator(value, axis);
    }

}
