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

package org.apache.isis.core.security.authentication.standard;

import org.apache.isis.applib.services.iactn.ExecutionContext;
import org.apache.isis.applib.services.user.UserMemento;
import org.apache.isis.core.security.authentication.AuthenticationSessionAbstract;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class SimpleSession
extends AuthenticationSessionAbstract {

    private static final long serialVersionUID = 1L;
    
    // -- FACTORIES
    
    public static SimpleSession of( 
            final @NonNull ExecutionContext executionEnvironment,
            final @NonNull String validationCode) {
        return new SimpleSession(executionEnvironment, validationCode);
    }
    
    public static SimpleSession validOf( 
            final @NonNull ExecutionContext executionEnvironment) {
        return of(executionEnvironment, AuthenticationSessionAbstract.DEFAULT_AUTH_VALID_CODE);
    }
    
    @Deprecated //
    public static SimpleSession ofUserWithSystemDefaults( 
            final @NonNull UserMemento user,
            final @NonNull String validationCode) {
        return of(ExecutionContext.ofUserWithSystemDefaults(user), validationCode);
    }
    
    @Deprecated //
    public static SimpleSession validOfUserWithSystemDefaults( 
            final @NonNull UserMemento user) {
        return validOf(ExecutionContext.ofUserWithSystemDefaults(user));
    }
    
    // -- CONSTRUCTOR
    
    public SimpleSession(
            final @NonNull ExecutionContext executionEnvironment, 
            final @NonNull String validationCode) {
        super(executionEnvironment, validationCode);
    }

    @Getter @Setter
    private Type type = Type.DEFAULT;

}
