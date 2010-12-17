/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */


package org.apache.isis.core.metamodel.facets;


import java.lang.reflect.Method;
import java.util.List;

import org.apache.isis.core.metamodel.spec.feature.ObjectFeatureType;


public interface FacetFactory {

    /**
     * The {@link ObjectFeatureType feature type}s that this facet factory can create {@link Facet}s for.
     * 
     * <p>
     * Used by the Java5 Reflector's <tt>ProgrammingModel</tt> to reduce the number of {@link FacetFactory factory}s that are
     * queried when building up the meta-model.
     */
    List<ObjectFeatureType> getFeatureTypes();

    /**
     * Process the class, and return the correctly setup annotation if present.
     * 
     * @param cls
     *            - class being processed
     * @param methodRemover
     *            - allow any methods of the class to be removed
     * @param holder
     *            - to attach the facets to
     * 
     * @return <tt>true</tt> if any facets were added, <tt>false</tt> otherwise.
     */
    boolean process(Class<?> cls, MethodRemover methodRemover, FacetHolder holder);

    /**
     * Process the method, and return the correctly setup annotation if present.
     * @param cls TODO
     * @param method
     *            - method representing the feature being processed (getter for property or collection, or
     *            action)
     * @param methodRemover
     *            - allow any methods of the class to be removed
     * @param holder
     *            - to attach the facets to
     * 
     * @return <tt>true</tt> if any facets were added and therefore should be removed, <tt>false</tt>
     *         otherwise. Returning true will cause the method to be removed
     */
    boolean process(Class<?> cls, Method method, MethodRemover methodRemover, FacetHolder holder);

    /**
     * Process the parameters of the method, and return the correctly setup annotation if present.
     * 
     * @param method
     *            - method representing the feature being processed (getter for property or collection, or
     *            action)
     * @param paramNum
     *            - 0-based index to the parameter to be processed.
     * @param holder
     *            - to attach the facets to
     * 
     * @return <tt>true</tt> if any facets were added, <tt>false</tt> otherwise.
     */
    boolean processParams(Method method, int paramNum, FacetHolder holder);

}
