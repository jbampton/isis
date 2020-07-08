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
package org.apache.isis.viewer.common.model.decorator.disable;

import java.io.Serializable;
import java.util.Optional;

import javax.annotation.Nullable;

import org.apache.isis.core.commons.internal.base._Strings;
import org.apache.isis.core.metamodel.interactions.managed.MemberInteraction;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PRIVATE)
public class DisablingUiModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NonNull final String reason;
    
    /**
     * @param disabled - overwritten to be {@code true}, whenever {@code reason} is not empty
     * @param reason
     * @deprecated use interaction instead
     */
    public static Optional<DisablingUiModel> of(boolean disabled, @Nullable String reason) {
        reason = _Strings.nullToEmpty(reason);
        disabled|=_Strings.isNotEmpty(reason);
        return disabled
                ? Optional.of(of(reason))
                : Optional.empty();
    }

    public static Optional<DisablingUiModel> of(@NonNull MemberInteraction<?, ?> memberInteraction) {
        return memberInteraction.getInteractionVeto()
                .map(veto->of(veto.getReason()));
    }

}
