package pl.edu.agh.iisg.timeline.testviewer;

public class Event {

	private String title;
	private String description;

	public Event(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

}
