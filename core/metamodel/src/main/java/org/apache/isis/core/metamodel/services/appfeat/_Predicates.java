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
package org.apache.isis.core.metamodel.services.appfeat;

import java.util.Objects;
import java.util.function.Predicate;

import org.apache.isis.applib.services.appfeat.ApplicationFeatureId;
import org.apache.isis.applib.services.appfeat.ApplicationFeatureSort;
import org.apache.isis.applib.services.appfeat.ApplicationMemberSort;

final class _Predicates {

    public static Predicate<ApplicationFeatureId> isLogicalTypeContaining(
            final ApplicationMemberSort memberSort, 
            final ApplicationFeatureRepositoryDefault applicationFeatures) {
        
        return (final ApplicationFeatureId featureId) -> {
                if(featureId.getSort() != ApplicationFeatureSort.TYPE) {
                    return false;
                }
                final ApplicationFeature feature = applicationFeatures.findFeature(featureId);
                if(feature == null) {
                    return false;
                }
                return memberSort == null 
                        || !feature.membersOf(memberSort).isEmpty();
        };
    }

    public static Predicate<ApplicationFeatureId> isLogicalTypeRecursivelyWithin(
            final ApplicationFeatureId namespaceId) {
        return (final ApplicationFeatureId input) -> 
            input.getPathIds().stream().skip(1L).anyMatch(id->Objects.equals(id, namespaceId));
    }

}
