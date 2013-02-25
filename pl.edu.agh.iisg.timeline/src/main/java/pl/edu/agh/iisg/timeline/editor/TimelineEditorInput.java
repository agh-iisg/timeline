/*
 * This file is a part of CAST project.
 * (c) Copyright 2007, AGH University of Science & Technology
 * https://caribou.iisg.agh.edu.pl/trac/cast
 *
 * Licensed under the Eclipse Public License, Version 1.0 (the "License").
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * File: TimelineEditorInput.java
 * Created: Feb 21, 2013
 * Author: mentel
 * $Id$
 */

package pl.edu.agh.iisg.timeline.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import pl.edu.agh.iisg.timeline.common.model.TimelineDiagram;

/**
 *
 * @author AGH CAST Team
 */
public class TimelineEditorInput implements IEditorInput {

    private static final String TIMELINE_EDITOR_INPUT_NAME = "Timeline editor input"; //$NON-NLS-1$

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
        return TIMELINE_EDITOR_INPUT_NAME;
    }

    @Override
    public IPersistableElement getPersistable() {
        return null;
    }

    @Override
    public String getToolTipText() {
        return TIMELINE_EDITOR_INPUT_NAME;
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
