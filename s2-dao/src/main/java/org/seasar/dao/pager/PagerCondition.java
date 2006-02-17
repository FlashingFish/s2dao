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

/**
 * �y�[�W�������I�u�W�F�N�g�̃C���^�[�t�F�C�X
 * 
 * @author Toshitaka Agata(Nulab,inc.)
 */
public interface PagerCondition {
    
    public static final int NONE_LIMIT = -1;
    
    /**
     * �������ʂ̑��������擾���܂��B
     * @return ������
     */
    public int getCount();
    
    /**
     * �������ʂ̑��������Z�b�g���܂��B
     * @param count ������
     */
    public void setCount(int count);
    
    /**
     * �������ʂ����x�Ɏ擾����ő匏�����擾���܂��B
     * @return �ő匏��
     */
    public int getLimit();
    
    /**
     * �������ʂ����x�Ɏ擾����ő匏�����Z�b�g���܂��B
     * @param limit �ő匏��
     */
    public void setLimit(int limit);
    
    /**
     * �������ʂ̎擾�J�n�ʒu�����Z�b�g���܂��B
     * @param offset �擾�J�n�ʒu
     */
    public void setOffset(int offset);

    /**
     * �������ʂ̎擾�J�n�ʒu�����擾���܂��B
     * @return �擾�J�n�ʒu
     */
    public int getOffset();
}