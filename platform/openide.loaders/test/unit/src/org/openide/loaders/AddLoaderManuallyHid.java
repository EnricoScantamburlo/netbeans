/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.openide.loaders;

import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import java.util.List;

/** Utility to add a loader to the loader pool.
 * Should only be used in external execution mode!
 * Requires core.jar in classpath.
 * @author Jesse Glick
 */
public final class AddLoaderManuallyHid {

    private AddLoaderManuallyHid() {}

    /** Add a loader to the pool (to the front of the free area). */
    public static void addRemoveLoader(DataLoader l, boolean add) throws Exception {
        // Initialize IDE:
//        TopManager.getDefault();
        
        // Now add the loader. Would be easy enough if we could directly access
        // core classes, but then this test would have to be compiled with core.jar
        // in the classpath...
        Class lpnClazz = Class.forName("org.netbeans.core.NbLoaderPool");
        Field loadersF = lpnClazz.getDeclaredField("loaders");
        loadersF.setAccessible(true);
        List loaders = (List)loadersF.get(null);
        if (add) {
            if (loaders.contains(l)) throw new IllegalArgumentException();
            loaders.add(0, l);
        } else {
            if (! loaders.contains(l)) throw new IllegalArgumentException();
            loaders.remove(l);
        }
        
        DataLoaderPool pool = DataLoaderPool.getDefault ();
        if (add) {
            l.addPropertyChangeListener((PropertyChangeListener)pool);
        } else {
            l.removePropertyChangeListener((PropertyChangeListener)pool);
        }
        // Simulate behavior of update(), but fire pool change immediately:
        Field loadersArrayF = lpnClazz.getDeclaredField("loadersArray");
        loadersArrayF.setAccessible(true);
        loadersArrayF.set(null, null);
        pool.fireChangeEvent(new ChangeEvent(pool));
    }
    
}
