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
package org.apache.isis.core.metamodel.adapter.oid;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.isis.applib.id.LogicalType;

public class OidVersionTest  {

    private LogicalType cus = LogicalTypeTestFactory.cus();
    private LogicalType ord = LogicalTypeTestFactory.ord();

    private RootOid oid1, oid2;

    @Test
    public void whenEquivalent() throws Exception {
        oid1 = Oid.Factory.root(cus, "123");
        oid2 = Oid.Factory.root(cus, "123");

        assertThat(oid1, is(equalTo(oid2)));
    }

    @Test
    public void whenNotEquivalentById() throws Exception {
        oid1 = Oid.Factory.root(cus, "123");
        oid2 = Oid.Factory.root(cus, "124");

        assertThat(oid1, is(not(equalTo(oid2))));
    }

    @Test
    public void whenNotEquivalentByObjectSpecId() throws Exception {
        oid1 = Oid.Factory.root(cus, "123");
        oid2 = Oid.Factory.root(ord, "123");

        assertThat(oid1, is(not(equalTo(oid2))));
    }


}
