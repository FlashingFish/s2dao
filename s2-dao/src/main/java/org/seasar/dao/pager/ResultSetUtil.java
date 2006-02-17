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

import org.seasar.framework.exception.SQLRuntimeException;

/**
 * ResultSet���[�e�B���e�B�ł��B
 * 
 * @author Toshitaka Agata(Nulab,inc.)
 */
class ResultSetUtil {
    
    /**
     * ResultSet���w�肳�ꂽ�ʒu�܂Ői�߂܂��B
     * @param resultSet ResultSet
     * @param offset �ʒu
     * @return ResultSet#next���Ăяo������
     * @throws SQLException
     */
    public static int autoAbsolute(ResultSet resultSet, int offset) throws SQLException {
        if (isCursorSupport(resultSet)) {
            try {
                if (offset != resultSet.getRow()) {
                    resultSet.absolute(offset);
                    return resultSet.getRow();
                }
            } catch (SQLException e) {
                return manualAbsolute(resultSet, offset);
            }
        } else {
        	return manualAbsolute(resultSet, offset);
        }
        return 0;
    }

    /**
     * ResultSet���w�肳�ꂽ�ʒu�܂Ői�߂܂��B
     * @param resultSet ResultSet
     * @param offset �ʒu
     * @return ResultSet#next���Ăяo������
     * @throws SQLException
     */
    private static int manualAbsolute(ResultSet resultSet, int offset) throws SQLException {
        int count = 0;
    	while(resultSet.getRow() < offset &&  resultSet.next()) {
    		count++;
        }
    	return count;
    }

    /**
     * ResultSet���Ō�̈ʒu�܂Ői�߂܂��B
     * @param resultSet ResultSet
     * @throws SQLException
     */
    public static boolean autoLast(ResultSet resultSet) throws SQLException {
        if (isCursorSupport(resultSet)) {
            try {
                resultSet.last();
                return true;
            } catch (SQLException e) {
                manualLast(resultSet);
                return false;
            }
        } else {
            manualLast(resultSet);
            return false;
        }
    }

    /**
     * ResultSet���J�[�\�����T�|�[�g���Ă��邩�ǂ����𔻒肵�܂��B
     * @param resultSet ResultSet
     * @return �J�[�\�����T�|�[�g���Ă����true�A����ȊO��false
     * @throws SQLException
     */
    public static boolean isCursorSupport(ResultSet resultSet) {
    	try {
            return !(resultSet.getType() == ResultSet.TYPE_FORWARD_ONLY);
    	} catch (SQLException e) {
    		throw new SQLRuntimeException(e);
    	}
    }

    /**
     * ResultSet���Ō�̈ʒu�܂Ői�߂܂��B
     * @param resultSet ResultSet
     * @throws SQLException
     */
    private static void manualLast(ResultSet resultSet) throws SQLException {
      	while (resultSet.next()) {
      	}
    }
}
