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
 * �y�[�W���̃r���[�w���p�[�N���X�ł��B
 * 
 * @author Toshitaka Agata(Nulab,inc.)
 */
public class PagerViewHelper implements PagerCondition {

    private static final int DEFAULT_DISPLAY_PAGE_MAX = 9;

    /** ���������I�u�W�F�N�g */
    private final PagerCondition condition;
    
    /** ��ʏ�ł̃y�[�W�̍ő�\���� */
    private final int displayPageMax;
    
    
    public PagerViewHelper(PagerCondition condition) {
        this(condition, DEFAULT_DISPLAY_PAGE_MAX);
    }

    public PagerViewHelper(PagerCondition condition, int displayPageMax) {
        this.condition = condition;
        this.displayPageMax = displayPageMax;
    }

    public int getCount() {
        return condition.getCount();
    }

    public void setCount(int count) {
        condition.setCount(count);
    }

    public int getLimit() {
        return condition.getLimit();
    }

    public void setLimit(int limit) {
        condition.setLimit(limit);
    }

    public int getOffset() {
        return condition.getOffset();
    }

    public void setOffset(int offset) {
        condition.setOffset(offset);
    }

    /**
     * �O�ւ̃����N���\���ł��邩�ǂ����𔻒肵�܂��B
     * @param ture/false
     */
    public boolean isPrev() {
        return PagerUtil.isPrev(condition);
    }

    /**
     * ���ւ̃����N���\���ł��邩�ǂ����𔻒肵�܂��B
     * @param ture/false
     */
    public boolean isNext() {
        return PagerUtil.isNext(condition);
    }

    /**
     * ���ݕ\�����̈ꗗ�̍Ō��offset���擾���܂��B
     * @param ���ݕ\�����̈ꗗ�̍Ō��offset
     */
    public int getCurrentLastOffset() {
        return PagerUtil.getCurrentLastOffset(condition);
    }
    
    /**
     * ���փ����N��offset��Ԃ��܂��B
     * @return ���փ����N��offset
     */
    public int getNextOffset() {
        return PagerUtil.getNextOffset(condition);
    }
    
    /**
     * �O�փ����N��offset��Ԃ��܂��B
     * @return �O�փ����N��offset
     */
    public int getPrevOffset() {
        return PagerUtil.getPrevOffset(condition);
    }
    
    /**
     * ���݃y�[�W�̃C���f�b�N�X��Ԃ��܂��B
     * @return ���݃y�[�W�̃C���f�b�N�X
     */
    public int getPageIndex() {
        return PagerUtil.getPageIndex(condition);
    }
    
    /**
     * ���݃y�[�W�̃J�E���g(�C���f�b�N�X+1)��Ԃ��܂��B
     * @return ���݃y�[�W�̃J�E���g(�C���f�b�N�X+1)
     */
    public int getPageCount() {
        return PagerUtil.getPageCount(condition);
    }
    
    /**
     * �ŏI�y�[�W�̃C���f�b�N�X��Ԃ��܂��B
     * @return �ŏI�y�[�W�̃C���f�b�N�X
     */
    public int getLastPageIndex() {
        return PagerUtil.getLastPageIndex(condition);
    }
    
    /**
     * �y�[�W�����N�̕\����������ɁA�y�[�W�ԍ������N�̕\���J�n�ʒu��Ԃ��܂��B
     * @return �y�[�W�ԍ������N�̕\���J�n�ʒu
     */
    public int getDisplayPageIndexBegin() {
        return PagerUtil.getDisplayPageIndexBegin(condition, displayPageMax);
    }

    /**
     * �y�[�W�����N�̕\����������ɁA�y�[�W�ԍ������N�̕\���I���ʒu��Ԃ��܂��B
     * @return �y�[�W�ԍ������N�̕\���I���ʒu
     */
    public int getDisplayPageIndexEnd() {
        return PagerUtil.getDisplayPageIndexEnd(condition, displayPageMax);
    }
}