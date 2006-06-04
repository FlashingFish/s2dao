package org.seasar.dao.pager;

import junit.framework.TestCase;

/**
 * @author Toshitaka Agata
 */
public class PagerResultSetFactoryLimitOffsetWapperTest extends TestCase {

    MockResultSetFactory original;

    PagerResultSetFactoryLimitOffsetWrapper wrapper;

    protected void setUp() throws Exception {
        original = new MockResultSetFactory();
        wrapper = new PagerResultSetFactoryLimitOffsetWrapper(original, "MySQL");
    }

    /*
     * public void testMakeBaseSql() throws Exception { try {
     * PagerContext.getContext().pushArgs(createNormalArgs()); assertEquals(
     * "SELECT�̑O�̃l�C�e�B�uSQL������", "SELECT * FROM DEPARTMENT",
     * wrapper.makeBaseSql("native sql ... SELECT * FROM DEPARTMENT"));
     * assertEquals( "�l�C�e�B�uSQL�����݂��Ȃ��ꍇ�A����SQL���ω��Ȃ�", "SELECT * FROM DEPARTMENT",
     * wrapper.makeBaseSql("SELECT * FROM DEPARTMENT")); } finally {
     * PagerContext.getContext().popArgs(); }
     *  }
     */
    public void testLimitOffsetSql() throws Exception {
        try {
            PagerContext.getContext().pushArgs(createNormalArgs());
            assertEquals("�w�肳�ꂽlimit offset���t�����ꂽSQL�𐶐�",
                    "SELECT * FROM DEPARTMENT LIMIT 10 OFFSET 55", wrapper
                            .makeLimitOffsetSql("SELECT * FROM DEPARTMENT", 10,
                                    55));
        } finally {
            PagerContext.getContext().popArgs();
        }

    }

    private Object[] createNormalArgs() {
        return new Object[] {};
    }
}
