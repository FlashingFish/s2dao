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
 * �y�[�W�������I�u�W�F�N�g�̃C���^�[�t�F�C�X
 * 
 * @author Toshitaka Agata(Nulab,inc.)
 */
public interface PagerCondition {

    public static final int NONE_LIMIT = -1;

    /**
     * �������ʂ̑��������擾���܂��B
     * 
     * @return ������
     */
    public int getCount();

    /**
     * �������ʂ̑��������Z�b�g���܂��B
     * 
     * @param count
     *            ������
     */
    public void setCount(int count);

    /**
     * �������ʂ����x�Ɏ擾����ő匏�����擾���܂��B
     * 
     * @return �ő匏��
     */
    public int getLimit();

    /**
     * �������ʂ����x�Ɏ擾����ő匏�����Z�b�g���܂��B
     * 
     * @param limit
     *            �ő匏��
     */
    public void setLimit(int limit);

    /**
     * �������ʂ̎擾�J�n�ʒu�����Z�b�g���܂��B
     * 
     * @param offset
     *            �擾�J�n�ʒu
     */
    public void setOffset(int offset);

    /**
     * �������ʂ̎擾�J�n�ʒu�����擾���܂��B
     * 
     * @return �擾�J�n�ʒu
     */
    public int getOffset();

}
