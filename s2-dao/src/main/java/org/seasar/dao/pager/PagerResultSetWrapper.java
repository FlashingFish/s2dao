/*
 * 
 * The Seasar Software License, Version 1.1
 *
 * Copyright (c) 2003-2004 The Seasar Project. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 *
 * 1. Redistributions of source code must retain the above 
 *    copyright notice, this list of conditions and the following 
 *    disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above 
 *    copyright notice, this list of conditions and the following 
 *    disclaimer in the documentation and/or other materials provided 
 *    with the distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgement:  
 *    "This product includes software developed by the 
 *    Seasar Project (http://www.seasar.org/)."
 *    Alternately, this acknowledgement may appear in the software
 *    itself, if and wherever such third-party acknowledgements 
 *    normally appear.
 *
 * 4. Neither the name "The Seasar Project" nor the names of its
 *    contributors may be used to endour or promote products derived 
 *    from this software without specific prior written permission of 
 *    the Seasar Project.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR 
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE SEASAR PROJECT 
 * OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
 * INCIDENTAL,SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS 
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY,OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF 
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
	private static final Log log = LogFactory.getLog(PagerResultSetWrapper.class);
	
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
    public PagerResultSetWrapper(ResultSet original, PagerCondition condition, boolean useAbsolute) {
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
		if (useAbsolute && ResultSetUtil.isCursorSupport(original)) {
			if(log.isDebugEnabled()) {
				log.debug("[S2Pager]Use scroll cursor.");
			}
			try {
				original.absolute(condition.getOffset());
				counter = original.getRow();
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			}
		} else {
			if(log.isDebugEnabled()) {
				log.debug("[S2Pager]Not use scroll cursor.");
			}
			try {
		    	while(original.getRow() < condition.getOffset() 
		    			&&  original.next()) {
		    		counter++;
		        }
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.seasar.dao.impl.ResultSetAdaptor#next()
	 */
	public boolean next() throws SQLException {
		boolean next = super.next();
		if ((condition.getLimit() == PagerCondition.NONE_LIMIT 
				|| counter < condition.getOffset() + condition.getLimit()) && next) {
			counter += 1;
			return true;
		} else {
			if (useAbsolute && ResultSetUtil.isCursorSupport(original)) {
	            original.last();
				int count = original.getRow();
				condition.setCount(count);
			} else {
				if (next) {
					counter++; // ����
			    	while(original.next()) {
			    		counter++;
			        }
				}
				condition.setCount(counter);
			}
			return false;
		}
	}
}
