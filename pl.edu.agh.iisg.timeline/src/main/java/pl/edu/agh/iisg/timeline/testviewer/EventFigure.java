package pl.edu.agh.iisg.timeline.testviewer;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

public class EventFigure extends RectangleFigure {

	private final static Color BACKGROUND_COLOR = new Color(
			Display.getCurrent(), 240, 240, 240);

	private final static Font TITLE_FONT = new Font(Display.getCurrent(),
			new FontData(Display.getCurrent().getSystemFont().getFontData()[0]
					.getName(), 10, SWT.BOLD));

	public EventFigure(String title, String description) {
		init();

		add(createTitleLabel(title));
		add(createDescriptionLabel(description));
	}

	private void init() {
		setBackgroundColor(BACKGROUND_COLOR);
		setLayoutManager(new ToolbarLayout());
		setPreferredSize(220, 70);
	}

	private Label createTitleLabel(String title) {
		Label label = new Label(title);
		label.setFont(TITLE_FONT);
		return label;
	}

	private IFigure createDescriptionLabel(String description) {
		return new Label(description);
	}
}
