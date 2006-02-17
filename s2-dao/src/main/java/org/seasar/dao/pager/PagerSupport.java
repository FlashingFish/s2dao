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

import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.util.ClassUtil;

/**
 * �y�[�W���Ǘ����[�e�B���e�B�N���X�B<p>
 * �Z�b�V�������̃y�[�W�����������I�u�W�F�N�g���Ǘ����܂��B<p>
 * <p>
 * �g�p���@�͈ȉ��̂悤�ɂȂ�܂��B
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
     * �R���X�g���N�^<p>
     * �ő�擾�����͖�����(-1)�ɐݒ肳��܂��B
     * @param pagerConditionClass �y�[�W�����������N���X
     * @param pagerConditionName ���������I�u�W�F�N�g�̃Z�b�V�������̖��O
     */
    public PagerSupport(Class pagerConditionClass, String pagerConditionName) {
        this(DEFAULT_LIMIT, pagerConditionClass, pagerConditionName);
    }
    
    /**
     * �R���X�g���N�^
     * @param limit �ő�擾����
     * @param pagerConditionClass �y�[�W�����������N���X
     * @param pagerConditionName ���������I�u�W�F�N�g�̃Z�b�V�������̖��O
     */
    public PagerSupport(int limit, Class pagerConditionClass, String pagerConditionName) {
        this.limit = limit;
        this.pagerConditionClass = pagerConditionClass;
        this.pagerConditionName = pagerConditionName;
    }
    
    /**
     * ���N�G�X�g�p�����[�^�����w�肵�āA�Z�b�V�������̌��������I�u�W�F�N�g�̌��݈ʒu���X�V���܂��B<p>
     * ���������I�u�W�F�N�g�����݂��Ȃ��ꍇ�A�V�K�Ɍ��������I�u�W�F�N�g�𐶐����܂��B
     * @param request HttpServletRequest
     * @param offsetParamName ���݈ʒu��\�����N�G�X�g�p�����[�^��
     */
    public void updateOffset(HttpServletRequest request, String offsetParamName) {
        int offset = getOffset(request, offsetParamName);
        PagerCondition pagerCondition = getPagerCondition(request);
        pagerCondition.setOffset(offset);
    }
    
    /**
     *  ���N�G�X�g�p�����[�^��"offset"�ŃZ�b�V�������̌��������I�u�W�F�N�g�̌��݈ʒu���X�V���܂��B<p>
     * ���������I�u�W�F�N�g�����݂��Ȃ��ꍇ�A�V�K�Ɍ��������I�u�W�F�N�g�𐶐����܂��B
     * @param request HttpServletRequest
     */
    public void updateOffset(HttpServletRequest request) {
        updateOffset(request, "offset");
    }
    
    /**
     * ���N�G�X�g�p�����[�^"offset"���猻�݈ʒu���擾���܂��B
     * @param request HttpServletRequest
     * @param offsetParamName ���݈ʒu��\�����N�G�X�g�p�����[�^��
     * @return�@���݈ʒu
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
     * �Z�b�V�������̌��������I�u�W�F�N�g���擾���܂��B<p>
     * ���������I�u�W�F�N�g�����݂��Ȃ��ꍇ�A�V�K�Ɍ��������I�u�W�F�N�g�𐶐����܂��B
     * @param request HttpServletRequest
     * @return ���������I�u�W�F�N�g
     */
    public PagerCondition getPagerCondition(HttpServletRequest request) {
        PagerCondition dto = (PagerCondition) request.getSession()
                .getAttribute(pagerConditionName);
        if (dto == null) {
            dto = (PagerCondition)ClassUtil.newInstance(pagerConditionClass);
            dto.setLimit(limit);
            request.getSession().setAttribute(pagerConditionName, dto);
        }
        return dto;
    }
    
}