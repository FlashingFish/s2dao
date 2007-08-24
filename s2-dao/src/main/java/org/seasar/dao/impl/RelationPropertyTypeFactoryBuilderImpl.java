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

import java.sql.DatabaseMetaData;

import org.seasar.dao.BeanAnnotationReader;
import org.seasar.dao.BeanMetaDataFactory;
import org.seasar.dao.RelationPropertyTypeFactory;
import org.seasar.dao.RelationPropertyTypeFactoryBuilder;

/**
 * {@link RelationPropertyTypeFactoryImpl}を組み立てる {@link RelationPropertyTypeFactoryBuilder}の実装クラスです。
 * 
 * @author taedium
 */
public class RelationPropertyTypeFactoryBuilderImpl implements
        RelationPropertyTypeFactoryBuilder {

    public RelationPropertyTypeFactory build(Class beanClass,
            BeanAnnotationReader beanAnnotationReader,
            BeanMetaDataFactory beanMetaDataFactory,
            DatabaseMetaData databaseMetaData, int relationNestLevel,
            boolean isStopRelationCreation) {

        return new RelationPropertyTypeFactoryImpl(beanClass,
                beanAnnotationReader, beanMetaDataFactory, databaseMetaData,
                relationNestLevel, isStopRelationCreation);
    }

}