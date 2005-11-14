package org.seasar.dao.impl;

import java.util.List;

/**
 * WEB ��ʗ��p����Dao�N���X<br>
 * <br>
 * File Information :<br>
 * 	$Header: /cvsroot/seasar/s2-dao/src/test/org/seasar/dao/impl/FormUseHistoryDao.java,v 1.1 2005/01/18 10:42:18 higa Exp $<br>
 *
 * @author ARGO21
 * @version 1.0
 */
public interface FormUseHistoryDao {
	/** BEAN�A�m�e�[�V���� */
	static final Class BEAN = FormUseHistory.class;
	
	//
	// �C���X�^���X���\�b�h
	//
	
	/**
	 * �C���T�[�g 
	 * @param formUseHistory WEB ��ʗ��p����
	 * @return �o�^������
	 */
	int insert(FormUseHistory formUseHistory);
	
	/** ARGS�A�m�e�[�V���� getEntity() */
	static final String getEntity_ARGS = "W_USER_CD,W_FORM_ID";
	
	/**
	 * �G���e�B�e�B�擾
	 * @param webUserCode
	 * @param webFormId
	 * @return WEB ��ʗ��p����
	 */
	FormUseHistory getEntity(String webUserCode,String webFormId);
	
	/**
	 * ���X�g�擾
	 * @return WEB ��ʗ��p�����̃��X�g
	 */
	List getList();
	
	//
	// �ǉ����\�b�h
	//
	
}

