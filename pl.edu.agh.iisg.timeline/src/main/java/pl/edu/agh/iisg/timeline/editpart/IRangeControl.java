package pl.edu.agh.iisg.timeline.editpart;

/**
 * A class designed to control and determine how much of the model space should be visible. When the scroll position if changed the range of
 * visible elements should change.
 *
 * @author leszko
 *
 */
public interface IRangeControl {

    void setScrollPosition(int position);

    boolean isChanged();

    int getStart();

    int getEnd();

}
