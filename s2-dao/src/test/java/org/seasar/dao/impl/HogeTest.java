package org.seasar.dao.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * @author higa
 *
 * ���̐������ꂽ�R�����g�̑}�������e���v���[�g��ύX���邽��
 * �E�B���h�E > �ݒ� > Java > �R�[�h���� > �R�[�h�ƃR�����g
 */
public class HogeTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(HogeTest.class);
	}

	public void testHoge() throws Exception {
		assertTrue(List.class.isAssignableFrom(ArrayList.class));
	}
}
