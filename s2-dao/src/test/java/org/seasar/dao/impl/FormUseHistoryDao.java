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

