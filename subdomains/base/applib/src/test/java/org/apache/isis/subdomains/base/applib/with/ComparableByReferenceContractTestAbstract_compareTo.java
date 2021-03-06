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
package org.apache.isis.subdomains.base.applib.with;

import java.util.Map;

import org.junit.Test;

public abstract class ComparableByReferenceContractTestAbstract_compareTo {
    protected final Iterable<Class<? extends WithReferenceComparable>> candidates;
    protected Map<Class<?>, Class<?>> noninstantiableSubstitutes;

    /**
     * @apiNote Usage example:<br>
     * {@code import org.reflections.Reflections;}<br>
     * {@code val reflections = new Reflections(packagePrefix);}<br>
     * {@code val candidates = reflections.getSubTypesOf(WithReferenceComparable.class);}
     */
    public ComparableByReferenceContractTestAbstract_compareTo(
            Iterable<Class<? extends WithReferenceComparable>> candidates, 
            Map<Class<?>, Class<?>> noninstantiableSubstitutes) {
        this.candidates = candidates;
        this.noninstantiableSubstitutes = noninstantiableSubstitutes;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void searchAndTest() {

        for (Class<? extends WithReferenceComparable> subtype : candidates) {
            if(subtype.isInterface() || subtype.isAnonymousClass() || subtype.isLocalClass() || subtype.isMemberClass()) {
                // skip (probably a testing class)
                continue;
            }
            subtype = instantiable(subtype);
            test(subtype);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Class<? extends WithReferenceComparable> instantiable(Class<? extends WithReferenceComparable> cls) {
        final Class<?> substitute = noninstantiableSubstitutes.get(cls);
        return (Class<? extends WithReferenceComparable>) (substitute!=null?substitute:cls);
    }

    private <T extends WithReferenceComparable<T>> void test(Class<T> cls) {
        new ComparableByReferenceContractTester<>(cls).test();
    }
}
