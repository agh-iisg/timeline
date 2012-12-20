package pl.edu.agh.iisg.timeline.editpart.dynamic;

/**
 * Default implementation range control. It has some inertia - not every change of the scroll provokes the change of range.
 *
 * @author leszko
 *
 */
public class DefaultRangeControl implements IRangeControl {

    private final int height;

    private final int inertia;

    private final int margin;

    private int position;

    private boolean changed;

    public DefaultRangeControl(int height, int inertia, int margin) {
        this.height = height;
        this.inertia = inertia;
        this.margin = margin;
        this.position = Integer.MIN_VALUE + inertia;
        this.changed = false;
    }

    public DefaultRangeControl() {
        this(850, 300, 4000);
    }

    @Override
    public void setScrollPosition(int position) {
        if (shouldChange(position)) {
            changed = true;
            changeRange(position);
        } else {
            changed = false;
        }

    }

    private boolean shouldChange(int position) {
        return position < this.position - inertia || position > this.position + height + inertia;
    }

    private void changeRange(int position) {
        this.position = position;
    }

    @Override
    public boolean isChanged() {
        return changed;
    }

    @Override
    public int getStart() {
        return Math.max(0, position - margin);
    }

    @Override
    public int getEnd() {
        return position + height + margin;
    }

}
