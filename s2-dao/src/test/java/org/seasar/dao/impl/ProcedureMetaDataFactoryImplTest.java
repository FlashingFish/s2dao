/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.dao.impl;

import org.seasar.dao.Dbms;
import org.seasar.dao.ProcedureMetaData;
import org.seasar.dao.ProcedureMetaDataFactory;
import org.seasar.dao.dbms.DbmsManager;
import org.seasar.extension.unit.S2TestCase;

/**
 * @author taedium
 *
 */
public class ProcedureMetaDataFactoryImplTest extends S2TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        include("j2ee-derby.dicon");
    }

    public void test() throws Exception {
        ProcedureMetaDataFactory factory = new ProcedureMetaDataFactoryImpl(
                getDataSource());
        Dbms dbms = DbmsManager.getDbms(getDatabaseMetaData());
        ProcedureMetaData metaData = factory.createProcedureMetaData(
                "PROCEDURE_TEST_CCC2", dbms, null);
        assertNotNull(metaData);
        assertEquals(3, metaData.getParameterTypeSize());
    }

}