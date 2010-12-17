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


package org.apache.isis.core.progmodel.facets.object.dirty;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.isis.core.metamodel.facets.Facet;
import org.apache.isis.core.metamodel.spec.feature.ObjectFeatureType;
import org.apache.isis.core.progmodel.facets.AbstractFacetFactoryTest;


public class DirtyMethodsFacetFactoryTest extends AbstractFacetFactoryTest {

    private DirtyMethodsFacetFactory facetFactory;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        facetFactory = new DirtyMethodsFacetFactory();
    }

    @Override
    protected void tearDown() throws Exception {
        facetFactory = null;
        super.tearDown();
    }

    @Override
    public void testFeatureTypes() {
        final List<ObjectFeatureType> featureTypes = facetFactory.getFeatureTypes();
        assertTrue(contains(featureTypes, ObjectFeatureType.OBJECT));
        assertFalse(contains(featureTypes, ObjectFeatureType.PROPERTY));
        assertFalse(contains(featureTypes, ObjectFeatureType.COLLECTION));
        assertFalse(contains(featureTypes, ObjectFeatureType.ACTION));
        assertFalse(contains(featureTypes, ObjectFeatureType.ACTION_PARAMETER));
    }

    public void testMarkDirtyMethodPickedUpOn() {
        @edu.umd.cs.findbugs.annotations.SuppressWarnings("UMAC_UNCALLABLE_METHOD_OF_ANONYMOUS_CLASS")
        class Customer {
            @SuppressWarnings("unused")
            public void markDirty() {};
        }
        final Method method = findMethod(Customer.class, "markDirty");

        facetFactory.process(Customer.class, methodRemover, facetHolder);

        final Facet facet = facetHolder.getFacet(MarkDirtyObjectFacet.class);
        assertNotNull(facet);
        assertTrue(facet instanceof MarkDirtyObjectFacetViaMethod);
        final MarkDirtyObjectFacetViaMethod markDirtyFacet = (MarkDirtyObjectFacetViaMethod) facet;
        assertEquals(method, markDirtyFacet.getMethods().get(0));

        assertTrue(methodRemover.getRemoveMethodMethodCalls().contains(method));
    }

    public void testIsDirtyMethodPickedUpOn() {
        @edu.umd.cs.findbugs.annotations.SuppressWarnings("UMAC_UNCALLABLE_METHOD_OF_ANONYMOUS_CLASS")
        class Customer {
            @SuppressWarnings("unused")
            public boolean isDirty() {
                return false;
            };
        }
        final Method method = findMethod(Customer.class, "isDirty");

        facetFactory.process(Customer.class, methodRemover, facetHolder);

        final Facet facet = facetHolder.getFacet(IsDirtyObjectFacet.class);
        assertNotNull(facet);
        assertTrue(facet instanceof IsDirtyObjectFacetViaMethod);
        final IsDirtyObjectFacetViaMethod isDirtyFacet = (IsDirtyObjectFacetViaMethod) facet;
        assertEquals(method, isDirtyFacet.getMethods().get(0));

        assertTrue(methodRemover.getRemoveMethodMethodCalls().contains(method));
    }

    public void testClearDirtyMethodPickedUpOn() {
        @edu.umd.cs.findbugs.annotations.SuppressWarnings("UMAC_UNCALLABLE_METHOD_OF_ANONYMOUS_CLASS")
        class Customer {
            @SuppressWarnings("unused")
            public void clearDirty() {};
        }
        final Method method = findMethod(Customer.class, "clearDirty");

        facetFactory.process(Customer.class, methodRemover, facetHolder);

        final Facet facet = facetHolder.getFacet(ClearDirtyObjectFacet.class);
        assertNotNull(facet);
        assertTrue(facet instanceof ClearDirtyObjectFacetViaMethod);
        final ClearDirtyObjectFacetViaMethod clearDirtyFacet = (ClearDirtyObjectFacetViaMethod) facet;
        assertEquals(method, clearDirtyFacet.getMethods().get(0));

        assertTrue(methodRemover.getRemoveMethodMethodCalls().contains(method));
    }

}

