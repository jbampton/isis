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
package org.apache.isis.core.runtime.iactn;

import org.apache.isis.applib.services.iactn.ExecutionContext;
import org.apache.isis.core.runtime.context.RuntimeContextBase;
import org.apache.isis.core.security.authentication.AuthenticationSession;

import lombok.Getter;
import lombok.NonNull;

/**
 * Provides the environment for an (or parts of an) user interaction to be executed.
 * <p>
 * Can be nested by pushing onto the current Thread's InteractionLayer Stack.  
 * 
 * @since 2.0
 *
 */
public class InteractionLayer extends RuntimeContextBase {

	@Getter private final InteractionSession interactionSession;
	@Getter private final AuthenticationSession authenticationSession;
	
	public InteractionLayer(
			final @NonNull InteractionSession interactionSession,
			final @NonNull AuthenticationSession authenticationSession) {

		super(interactionSession.getMetaModelContext());
		
		// current thread's InteractionSession which this layer belongs to, 
		// meaning the InteractionSession that holds the stack containing this layer 
		this.interactionSession = interactionSession;
		
		// binds given authenticationSession to this layer 
		this.authenticationSession = authenticationSession; 
	}
	
	public ExecutionContext getExecutionContext() {
	    return authenticationSession.getExecutionContext();
	}

}