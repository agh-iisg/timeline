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
 * File: PropertyChangeProviderHelper.java
 * Created: 2007-00-00
 * Author: awos
 * $Id: PropertyChangeProviderHelper.java 4917 2010-07-14 14:19:03Z czerwin $
 */

package pl.edu.agh.iisg.common.property;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * A property change provider that can be used to delegate to from other {@link IPropertyChangeProvider} implementations.
 *
 * @author AGH CAST Team
 */
public class PropertyChangeProviderHelper implements IPropertyChangeProvider, Serializable {

    private static final long serialVersionUID = 7575012122681385289L;

    private IPropertyChangeProvider source;

    private boolean suppressAllFlag;

    /**
     * The {@link PropertyChangeSupport} utility class. It automatically serializes all listeners that are {@link Serializable}, and ignores
     * the rest.
     */
    private PropertyChangeSupport pcsDelegate;

    /**
     * Creates new PCPHelper.
     *
     * @param source
     *            the source of events
     */
    public PropertyChangeProviderHelper(IPropertyChangeProvider source) {
        this.source = source;
        suppressAllFlag = false;
    }

    protected PropertyChangeSupport getPcsDelegate() {
        if (pcsDelegate == null) {
            pcsDelegate = new PropertyChangeSupport(source);
        }
        return pcsDelegate;
    }

    /**
     * {@inheritDoc}
     *
     * @see pl.edu.agh.cast.common.property.IPropertyChangeProvider #addPropertyChangeListener(java.beans.PropertyChangeListener)
     */
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException();
        }
        getPcsDelegate().addPropertyChangeListener(listener);
    }

    /**
     * {@inheritDoc}
     *
     * @see pl.edu.agh.cast.common.property.IPropertyChangeProvider #removePropertyChangeListener(java.beans.PropertyChangeListener)
     */
    public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
        if (l != null) {
            getPcsDelegate().removePropertyChangeListener(l);
        }
    }

    /**
     * Fires property change event.
     *
     * @param property
     *            the property
     * @param oldValue
     *            old property value
     * @param newValue
     *            new property value
     */
    public void firePropertyChange(String property, Object oldValue, Object newValue) {
        if (!suppressAllFlag && getPcsDelegate().hasListeners(property)) {
            getPcsDelegate().firePropertyChange(property, oldValue, newValue);
        }
    }

    /**
     * Returns suppress all events flag. If true, any property change won't generate property change event.
     *
     * @return suppress all events flag
     */
    public boolean isSuppressAllEvents() {
        return suppressAllFlag;
    }

    /**
     * Sets suppress all events flag. If flag set to <code>true</code> all events will be ignored.
     *
     * @param flag
     *            suppress all events flag
     */
    public void setSuppressAllEvents(boolean flag) {
        this.suppressAllFlag = flag;
    }

}
