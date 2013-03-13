package pl.edu.agh.iisg.timeline.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import pl.edu.agh.iisg.timeline.model.TimelineDiagram;

/**
 *
 * @author AGH CAST Team
 */
public class TimelineEditorInput implements IEditorInput {

    private static final String TIMELINE_EDITOR_NAME = "Timeline editor"; //$NON-NLS-1$

    private TimelineDiagram diagram;

    @SuppressWarnings("rawtypes")
    @Override
    public Object getAdapter(Class adapter) {
        return null;
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return null;
    }

    @Override
    public String getName() {
        return TIMELINE_EDITOR_NAME;
    }

    @Override
    public IPersistableElement getPersistable() {
        return null;
    }

    @Override
    public String getToolTipText() {
        return TIMELINE_EDITOR_NAME;
    }

    /**
     * Returns the timeline diagram associated with the input.
     *
     * @return the diagram
     */
    public TimelineDiagram getTimelineDiagram() {
        return diagram;
    }

    /**
     * Sets the timeline diagram for this input.
     *
     * @param aDiagram
     *            the diagram
     */
    public void setTimelineDiagram(TimelineDiagram aDiagram) {
        this.diagram = aDiagram;
    }

}
