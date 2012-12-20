package pl.edu.agh.iisg.timeline.editpart;

public interface IRangeControl {

	void setScrollPosition(int position);

	boolean isChanged();

	int getStart();

	int getEnd();

}
