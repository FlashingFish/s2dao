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

import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.util.ClassUtil;

/**
 * �y�[�W���Ǘ����[�e�B���e�B�N���X�B
 * <p>
 * �Z�b�V�������̃y�[�W�����������I�u�W�F�N�g���Ǘ����܂��B
 * <p>
 * <p>
 * �g�p���@�͈ȉ��̂悤�ɂȂ�܂��B
 * 
 * <pre>
 * public class XXXXAction extends Action {
 *   private PagerSupport pager = new Pager(20, MyPagerCondition.class, "myPagerCondition");
 *   private MyLogic logic;
 *   public void setMyLogic(MyLogic logic) {
 *     this.logic = logic;
 *   }
 * 
 *   public ActionForward doExecute(ActionMapping mapping, ActionForm _form,
 *       HttpServletRequest request, HttpServletResponse response) throws Exception {
 * 
 *      // �p�����[�^offset�����Ƀy�[�W����offset�ʒu���X�V
 *      pager.updateOffset(request);
 *      // ����
 *  	MyPagerCondition dto = (MyPagerCondition) pager.getPagerCondition(request);
 *       if (form.getCode() != null) {
 *           // ���������݂���΁A�������Z�b�g����B
 *           dto.setCode(form.getCode());
 *       }
 *       List items = logic.getItems(dto);
 *       request.setAttribute("items", items);
 *   }
 * }
 * </pre>
 * 
 * @author Toshitaka Agata(Nulab,inc.)
 */
public class PagerSupport {

    /** �ő�擾�����̏����l */
    public static final int DEFAULT_LIMIT = PagerCondition.NONE_LIMIT;

    /** �ő�擾���� */
    private int limit = DEFAULT_LIMIT;

    /** �y�[�W�����������N���X */
    private Class pagerConditionClass;

    /** ���������I�u�W�F�N�g�̃Z�b�V�������̖��O */
    private String pagerConditionName;

    /**
     * �R���X�g���N�^
     * <p>
     * �ő�擾�����͖�����(-1)�ɐݒ肳��܂��B
     * 
     * @param pagerConditionClass
     *            �y�[�W�����������N���X
     * @param pagerConditionName
     *            ���������I�u�W�F�N�g�̃Z�b�V�������̖��O
     */
    public PagerSupport(Class pagerConditionClass, String pagerConditionName) {
        this(DEFAULT_LIMIT, pagerConditionClass, pagerConditionName);
    }

    /**
     * �R���X�g���N�^
     * 
     * @param limit
     *            �ő�擾����
     * @param pagerConditionClass
     *            �y�[�W�����������N���X
     * @param pagerConditionName
     *            ���������I�u�W�F�N�g�̃Z�b�V�������̖��O
     */
    public PagerSupport(int limit, Class pagerConditionClass,
            String pagerConditionName) {
        this.limit = limit;
        this.pagerConditionClass = pagerConditionClass;
        this.pagerConditionName = pagerConditionName;
    }

    /**
     * ���N�G�X�g�p�����[�^�����w�肵�āA�Z�b�V�������̌��������I�u�W�F�N�g�̌��݈ʒu���X�V���܂��B
     * <p>
     * ���������I�u�W�F�N�g�����݂��Ȃ��ꍇ�A�V�K�Ɍ��������I�u�W�F�N�g�𐶐����܂��B
     * 
     * @param request
     *            HttpServletRequest
     * @param offsetParamName
     *            ���݈ʒu��\�����N�G�X�g�p�����[�^��
     */
    public void updateOffset(HttpServletRequest request, String offsetParamName) {
        int offset = getOffset(request, offsetParamName);
        PagerCondition pagerCondition = getPagerCondition(request);
        pagerCondition.setOffset(offset);
    }

    /**
     * ���N�G�X�g�p�����[�^��"offset"�ŃZ�b�V�������̌��������I�u�W�F�N�g�̌��݈ʒu���X�V���܂��B
     * <p>
     * ���������I�u�W�F�N�g�����݂��Ȃ��ꍇ�A�V�K�Ɍ��������I�u�W�F�N�g�𐶐����܂��B
     * 
     * @param request
     *            HttpServletRequest
     */
    public void updateOffset(HttpServletRequest request) {
        updateOffset(request, "offset");
    }

    /**
     * ���N�G�X�g�p�����[�^"offset"���猻�݈ʒu���擾���܂��B
     * 
     * @param request
     *            HttpServletRequest
     * @param offsetParamName
     *            ���݈ʒu��\�����N�G�X�g�p�����[�^��
     * @return ���݈ʒu
     */
    private int getOffset(HttpServletRequest request, String offsetParamName) {
        String value = request.getParameter(offsetParamName);
        if (value == null || value.length() == 0) {
            return 0;
        } else {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }

    /**
     * �Z�b�V�������̌��������I�u�W�F�N�g���擾���܂��B
     * <p>
     * ���������I�u�W�F�N�g�����݂��Ȃ��ꍇ�A�V�K�Ɍ��������I�u�W�F�N�g�𐶐����܂��B
     * 
     * @param request
     *            HttpServletRequest
     * @return ���������I�u�W�F�N�g
     */
    public PagerCondition getPagerCondition(HttpServletRequest request) {
        PagerCondition dto = (PagerCondition) request.getSession()
                .getAttribute(pagerConditionName);
        if (dto == null) {
            dto = (PagerCondition) ClassUtil.newInstance(pagerConditionClass);
            dto.setLimit(limit);
            request.getSession().setAttribute(pagerConditionName, dto);
        }
        return dto;
    }

}