package org.seasar.dao.pager;

/**
 * @author agata
 *
 * TODO ���̐������ꂽ�^�R�����g�̃e���v���[�g��ύX����ɂ͎��փW�����v:
 * �E�B���h�E - �ݒ� - Java - �R�[�h�E�X�^�C�� - �R�[�h�E�e���v���[�g
 */
public class TestUtil {

	public static void setUseAbsolute(boolean useAbsolute) {
		PagerResultSetFactoryWrapper.useScrollCursor = useAbsolute;
	}
}
