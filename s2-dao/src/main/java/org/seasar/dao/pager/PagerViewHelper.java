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
     * 
     * @param ture/false
     */
    public boolean isPrev() {
        return PagerUtil.isPrev(condition);
    }

    /**
     * ���ւ̃����N���\���ł��邩�ǂ����𔻒肵�܂��B
     * 
     * @param ture/false
     */
    public boolean isNext() {
        return PagerUtil.isNext(condition);
    }

    /**
     * ���ݕ\�����̈ꗗ�̍Ō��offset���擾���܂��B
     * 
     * @param ���ݕ\�����̈ꗗ�̍Ō��offset
     */
    public int getCurrentLastOffset() {
        return PagerUtil.getCurrentLastOffset(condition);
    }

    /**
     * ���փ����N��offset��Ԃ��܂��B
     * 
     * @return ���փ����N��offset
     */
    public int getNextOffset() {
        return PagerUtil.getNextOffset(condition);
    }

    /**
     * �O�փ����N��offset��Ԃ��܂��B
     * 
     * @return �O�փ����N��offset
     */
    public int getPrevOffset() {
        return PagerUtil.getPrevOffset(condition);
    }

    /**
     * ���݃y�[�W�̃C���f�b�N�X��Ԃ��܂��B
     * 
     * @return ���݃y�[�W�̃C���f�b�N�X
     */
    public int getPageIndex() {
        return PagerUtil.getPageIndex(condition);
    }

    /**
     * ���݃y�[�W�̃J�E���g(�C���f�b�N�X+1)��Ԃ��܂��B
     * 
     * @return ���݃y�[�W�̃J�E���g(�C���f�b�N�X+1)
     */
    public int getPageCount() {
        return PagerUtil.getPageCount(condition);
    }

    /**
     * �ŏI�y�[�W�̃C���f�b�N�X��Ԃ��܂��B
     * 
     * @return �ŏI�y�[�W�̃C���f�b�N�X
     */
    public int getLastPageIndex() {
        return PagerUtil.getLastPageIndex(condition);
    }

    /**
     * �y�[�W�����N�̕\����������ɁA�y�[�W�ԍ������N�̕\���J�n�ʒu��Ԃ��܂��B
     * 
     * @return �y�[�W�ԍ������N�̕\���J�n�ʒu
     */
    public int getDisplayPageIndexBegin() {
        return PagerUtil.getDisplayPageIndexBegin(condition, displayPageMax);
    }

    /**
     * �y�[�W�����N�̕\����������ɁA�y�[�W�ԍ������N�̕\���I���ʒu��Ԃ��܂��B
     * 
     * @return �y�[�W�ԍ������N�̕\���I���ʒu
     */
    public int getDisplayPageIndexEnd() {
        return PagerUtil.getDisplayPageIndexEnd(condition, displayPageMax);
    }
}