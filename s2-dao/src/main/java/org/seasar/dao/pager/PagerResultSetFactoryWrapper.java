/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.dao.pager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.seasar.extension.jdbc.ResultSetFactory;

/**
 * ResultSetFactory�����b�v���āA �y�[�W���p��ResultSet�𐶐����܂��B
 * 
 * @author Toshitaka Agata(Nulab,inc.)
 * @author manhole
 */
public class PagerResultSetFactoryWrapper implements ResultSetFactory {

    /** �I���W�i����ResultSetFactory */
    private ResultSetFactory resultSetFactory_;

    private boolean useScrollCursor_ = true;

    /**
     * �R���X�g���N�^
     * 
     * @param resultSetFactory
     *            �I���W�i����ResultSetFactory
     */
    public PagerResultSetFactoryWrapper(ResultSetFactory resultSetFactory) {
        resultSetFactory_ = resultSetFactory;
    }

    /**
     * @param b
     */
    public void setUseScrollCursor(boolean useScrollCursor) {
        useScrollCursor_ = useScrollCursor;
    }

    /**
     * ResultSet�𐶐����܂��B
     * <p>
     * PagerContext��PagerCondition���Z�b�g����Ă���ꍇ�A
     * ResultSet��PagerResultSetWrapper�Ń��b�v���ĕԂ��܂��B
     * 
     * @param PreparedStatement
     * @return ResultSet
     */
    public ResultSet createResultSet(PreparedStatement ps) {
        ResultSet resultSet = resultSetFactory_.createResultSet(ps);
        Object[] args = PagerContext.getContext().peekArgs();
        if (PagerContext.isPagerCondition(args)) {
            PagerCondition condition = PagerContext.getPagerCondition(args);
            return new PagerResultSetWrapper(resultSet, condition,
                    useScrollCursor_);
        } else {
            return resultSet;
        }
    }

}
