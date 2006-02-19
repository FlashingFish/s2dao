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
import java.sql.SQLException;

import org.seasar.extension.jdbc.ResultSetFactory;
import org.seasar.framework.exception.SQLRuntimeException;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.ResultSetUtil;
import org.seasar.framework.util.StatementUtil;

/**
 * @author yamamoto
 * @author agata
 */
public class PagerResultSetFactoryLimitOffsetWrapper implements
        ResultSetFactory {

    private static final Logger LOGGER = Logger
            .getLogger(PagerResultSetFactoryLimitOffsetWrapper.class);

    /** �I���W�i����ResultSetFactory */
    private ResultSetFactory resultSetFactory_;

    /**
     * �R���X�g���N�^
     * 
     * @param resultSetFactory
     *            �I���W�i����ResultSetFactory
     */
    public PagerResultSetFactoryLimitOffsetWrapper(
            ResultSetFactory resultSetFactory) {
        resultSetFactory_ = resultSetFactory;
    }

    /**
     * ResultSet�𐶐����܂��B<br>
     * PagerContext��PagerCondition���Z�b�g����Ă���ꍇ�A
     * <ul>
     * <li>�������ʌ������擾��PagerCondition�ɃZ�b�g���܂��B</li>
     * <li>LIMIT OFFSET ������t������SQL�����s���A���ʂ�ResultSet��Ԃ��܂��B</li>
     * </ul>
     * 
     * @param PreparedStatement
     * @return ResultSet
     */
    public ResultSet createResultSet(PreparedStatement ps) {

        Object[] args = PagerContext.getContext().peekArgs();

        if (PagerContext.isPagerCondition(args)) {
            try {
				String baseSQL = makeBaseSql(ps.toString());
                PagerCondition dto = PagerContext.getPagerCondition(args);
				dto.setCount(getCount(ps, baseSQL));
				if (dto.getLimit() > 0 && dto.getOffset() > -1) {
					String limitOffsetSql = 
                		makeLimitOffsetSql(baseSQL, dto.getLimit(), dto.getOffset());
					LOGGER.debug("S2Pager execute SQL : " + limitOffsetSql);

                    return resultSetFactory_.createResultSet(ps
                            .getConnection().prepareStatement(
                                    limitOffsetSql));
                } else {
                    return resultSetFactory_.createResultSet(ps);
                }
            } catch (SQLException e) {
                throw new SQLRuntimeException(e);
            }
        } else {
            return resultSetFactory_.createResultSet(ps);
        }
    }
    /**
     * limit offset��t������SQL���쐬���܂��B
     * @param baseSQL
     * @param limit
     * @param offset
     * @return
     */
	String makeLimitOffsetSql(String baseSQL, int limit, int offset) {
		StringBuffer sqlBuf = new StringBuffer(baseSQL);
		sqlBuf.append(" LIMIT ");
		sqlBuf.append(limit);
		sqlBuf.append(" OFFSET ");
		sqlBuf.append(offset);
		return sqlBuf.toString();
	}
    /**
     * JDBC�h���C�o�ɂ���ẮAselect�̑O�ɗ]�v�ȕ�����
     * �t������邽��select�̑O���������ăI���W�i����SQL���擾���܂��B
     * @param nativeSql JDBC�h���C�o�ŗL��SQL
     * @return �I���W�i����SQL
     */
	String makeBaseSql(String nativeSql) {
		return nativeSql.replaceFirst("^.*SELECT",
		        "SELECT");
	}
    /**
     * ����SQL�ɂ�錋�ʑ��������擾���܂�
     * @param ps ����PreparedStatement
     * @param baseSQL ����SQL
     * @return ���ʑ�����
     * @throws SQLException
     */
	private int getCount(PreparedStatement ps, String baseSQL) throws SQLException {
		StringBuffer sqlBuf = new StringBuffer("SELECT count(*) FROM (");
		sqlBuf.append(baseSQL);
		sqlBuf.append(") AS total");
		LOGGER.debug("S2Pager execute SQL : " + sqlBuf.toString());

		PreparedStatement psCount = null;
		ResultSet rs = null;
		try {
			psCount = ps.getConnection()
	        .prepareStatement(sqlBuf.toString());
			rs = resultSetFactory_.createResultSet(psCount);
			if (rs.next()) {
			    return rs.getInt(1);
			} else {
			    throw new SQLException("[S2Pager]Result not found.");
			}
		} finally {
			ResultSetUtil.close(rs);
			StatementUtil.close(psCount);
		}
	}

}
