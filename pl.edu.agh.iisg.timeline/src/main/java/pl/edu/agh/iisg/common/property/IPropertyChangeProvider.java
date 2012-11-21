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
 * File: IPropertyChangeProvider.java
 * Created: 2007-00-00
 * Author: awos
 * $Id: IPropertyChangeProvider.java 9440 2012-01-20 13:12:00Z czerwin $
 */

package pl.edu.agh.iisg.common.property;

import java.beans.PropertyChangeListener;

/**
 * This is an interface that defines methods for adding and removing property change listeners.
 *
 * @author AGH CAST Team
 */
public interface IPropertyChangeProvider {

    /**
     * Add a property change listener to the object.
     *
     * @param l
     *            property change listener
     */
    public void addPropertyChangeListener(PropertyChangeListener l);

    /**
     * Remove a property change listener to the object.
     *
     * @param l
     *            property change listener
     */
    public void removePropertyChangeListener(PropertyChangeListener l);

}
