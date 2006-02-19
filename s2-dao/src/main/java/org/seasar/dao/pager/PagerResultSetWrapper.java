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

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.extension.jdbc.impl.ResultSetWrapper;
import org.seasar.framework.exception.SQLRuntimeException;

/**
 * �y�[�W���p��ResultSet���b�p�[�B<p>
 * ���������I�u�W�F�N�g��offset�ʒu����Alimit�܂ł͈̔͂̌��ʂ�
 * next���\�b�h�ŕԂ��܂��B<p>
 * limit��-1�̏ꍇ�A�S�Ă̌��ʂ�next���\�b�h�ŕԂ��܂��B
 * 
 * @author Toshitaka Agata(Nulab,inc.)
 */
class PagerResultSetWrapper extends ResultSetWrapper {

    /** ���O */
    private static final Log log = LogFactory
            .getLog(PagerResultSetWrapper.class);

    /** �J�E���g */
    private int counter = 0;

    /** �I���W�i����ResultSet */
    private ResultSet original;

    /** ���������I�u�W�F�N�g */
    private PagerCondition condition;

    /** absolute���\�b�h���g�p���邩�ǂ����̃t���O */
    private boolean useAbsolute = true;

    public void setUseAbsolute(boolean useAbsolute) {
        this.useAbsolute = useAbsolute;
    }

    /**
     * �R���X�g���N�^
     * @param original �I���W�i����ResultSet 
     * @param condition ���������I�u�W�F�N�g
     * @param useAbsolute
     * @throws SQLException
     */
    public PagerResultSetWrapper(ResultSet original, PagerCondition condition,
            boolean useAbsolute) {
        super(original);
        this.original = original;
        this.condition = condition;
        this.useAbsolute = useAbsolute;
        moveOffset();
    }

    /**
     * �J�n�ʒu�܂ŃJ�[�\����i�߂܂��B
     * @throws SQLException
     */
    private void moveOffset() {
        if (isUseCursor()) {
            if (log.isDebugEnabled()) {
                log.debug("[S2Pager]Use scroll cursor.");
            }
            try {
                original.absolute(condition.getOffset());
                counter = original.getRow();
            } catch (SQLException e) {
                throw new SQLRuntimeException(e);
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug("[S2Pager]Not use scroll cursor.");
            }
            try {
                while (original.getRow() < condition.getOffset()
                        && original.next()) {
                    counter++;
                }
            } catch (SQLException e) {
                throw new SQLRuntimeException(e);
            }
        }
    }

    public boolean next() throws SQLException {
        boolean next = super.next();
        if ((condition.getLimit() == PagerCondition.NONE_LIMIT || counter < condition
                .getOffset()
                + condition.getLimit())
                && next) {
            counter += 1;
            return true;
        } else {
            if (isUseCursor()) {
                original.last();
                int count = original.getRow();
                condition.setCount(count);
            } else {
                if (next) {
                    counter++; // ����
                    while (original.next()) {
                        counter++;
                    }
                }
                condition.setCount(counter);
            }
            return false;
        }
    }

    private boolean isUseCursor() {
        return useAbsolute && ResultSetUtil.isCursorSupport(original);
    }

}
