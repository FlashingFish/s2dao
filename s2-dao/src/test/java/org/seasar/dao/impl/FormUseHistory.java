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

import java.io.Serializable;

/**
 * WEB ��ʗ��p�����N���X<br>
 * <br>
 * File Information :<br>
 * 	$Header: /cvsroot/seasar/s2-dao/src/test/org/seasar/dao/impl/FormUseHistory.java,v 1.1 2005/01/18 10:42:18 higa Exp $<br>
 *
 * @author ARGO21
 * @version 1.0
 */
public class FormUseHistory implements Serializable {
	//
	// �萔
	//
	
	/** TABLE�A�m�e�[�V���� */
	public static final String TABLE = "CWEB_FORM_HIST";
	
	/** COLUMN�A�m�e�[�V���� WEB���[�U�R�[�h */
	public static final String webUserCode_COLUMN = "W_USER_CD";
	
	/** COLUMN�A�m�e�[�V���� WEB���ID */
	public static final String webFormId_COLUMN = "W_FORM_ID";
	
	/** COLUMN�A�m�e�[�V���� �Q�ƃ^�C���X�^���v */
	public static final String referenceTimestamp_COLUMN = "REF_TIMESTAMP";
	
	/** COLUMN�A�m�e�[�V���� �Q�ƃz�X�gIP */
	public static final String referenceHostIp_COLUMN = "REF_HOST_IP";
	
	//
	// �C���X�^���X�t�B�[���h
	//
	
	/** WEB���[�U�R�[�h */
	private String webUserCode;
	
	/** WEB���ID */
	private String webFormId;
	
	/** �Q�ƃ^�C���X�^���v */
	private java.sql.Timestamp referenceTimestamp;
	
	/** �Q�ƃz�X�gIP */
	private String referenceHostIp;
	
	//
	// �C���X�^���X���\�b�h
	//
	
	/**
	 * WEB���[�U�R�[�h�擾
	 * @return String
	 */
	public String getWebUserCode() {
		return this.webUserCode;
	}
	/**
	 * WEB���[�U�R�[�h�ݒ�
	 * @param webUserCode
	 */
	public void setWebUserCode(String webUserCode) {
		this.webUserCode = webUserCode;
	}
	/**
	 * WEB���ID�擾
	 * @return String
	 */
	public String getWebFormId() {
		return this.webFormId;
	}
	/**
	 * WEB���ID�ݒ�
	 * @param webFormId
	 */
	public void setWebFormId(String webFormId) {
		this.webFormId = webFormId;
	}
	/**
	 * �Q�ƃ^�C���X�^���v�擾
	 * @return java.sql.Timestamp
	 */
	public java.sql.Timestamp getReferenceTimestamp() {
		return this.referenceTimestamp;
	}
	/**
	 * �Q�ƃ^�C���X�^���v�ݒ�
	 * @param referenceTimestamp
	 */
	public void setReferenceTimestamp(java.sql.Timestamp referenceTimestamp) {
		this.referenceTimestamp = referenceTimestamp;
	}
	/**
	 * �Q�ƃz�X�gIP�擾
	 * @return String
	 */
	public String getReferenceHostIp() {
		return this.referenceHostIp;
	}
	/**
	 * �Q�ƃz�X�gIP�ݒ�
	 * @param referenceHostIp
	 */
	public void setReferenceHostIp(String referenceHostIp) {
		this.referenceHostIp = referenceHostIp;
	}
	/**
	 * ������
	 * @return ������
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("webUserCode[").append(this.webUserCode).append("]");
		buffer.append("webFormId[").append(this.webFormId).append("]");
		buffer.append("referenceTimestamp[").append(this.referenceTimestamp).append("]");
		buffer.append("referenceHostIp[").append(this.referenceHostIp).append("]");
		return buffer.toString();
	}
}

