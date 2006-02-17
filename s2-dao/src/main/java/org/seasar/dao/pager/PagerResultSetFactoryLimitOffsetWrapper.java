package org.seasar.dao.pager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.seasar.extension.jdbc.ResultSetFactory;
import org.seasar.framework.exception.SQLRuntimeException;
import org.seasar.framework.log.Logger;

/**
 * @author yamamoto
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
                String baseSQL = ps.toString().replaceFirst("^.*SELECT",
                        "SELECT");
                StringBuffer sqlBuf = new StringBuffer("SELECT count(*) FROM (");
                sqlBuf.append(baseSQL);
                sqlBuf.append(") AS total");

                LOGGER.debug("S2Pager execute SQL : " + sqlBuf.toString());

                PreparedStatement psCount = ps.getConnection()
                        .prepareStatement(sqlBuf.toString());
                ResultSet rs = resultSetFactory_.createResultSet(psCount);

                if (rs.next()) {
                    PagerCondition dto = PagerContext.getPagerCondition(args);
                    dto.setCount(rs.getInt(1));

                    if (dto.getLimit() > 0 && dto.getOffset() > -1) {
                        sqlBuf = new StringBuffer(baseSQL);
                        sqlBuf.append(" LIMIT ");
                        sqlBuf.append(dto.getLimit());
                        sqlBuf.append(" OFFSET ");
                        sqlBuf.append(dto.getOffset());

                        LOGGER.debug("S2Pager execute SQL : "
                                + sqlBuf.toString());

                        rs = resultSetFactory_.createResultSet(ps
                                .getConnection().prepareStatement(
                                        sqlBuf.toString()));

                    } else {
                        rs = resultSetFactory_.createResultSet(ps);
                    }
                    return rs;
                } else {
                    throw new SQLException();
                }

            } catch (SQLException e) {
                throw new SQLRuntimeException(e);
            }

        } else {
            return resultSetFactory_.createResultSet(ps);
        }
    }

}
