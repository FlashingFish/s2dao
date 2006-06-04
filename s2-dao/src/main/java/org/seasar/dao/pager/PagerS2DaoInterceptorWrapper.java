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

import java.sql.PreparedStatement;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.dao.interceptors.S2DaoInterceptor;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

/**
 * �y�[�W���p��S2DaoInterceptor�̃��b�p�[�B PagerContext�Ɉ������Z�b�g���āA S2DaoInterceptor���Ăяo���܂��B
 * <p>
 * 
 * ���̃V�[�P���X�ɂ��A�y�[�W���O���������s����܂��B
 * <ol>
 * <li>PagerS2DaoInterceptorWrapper��������PagerContext�ɃZ�b�g���܂��B</li>
 * <li>PagerResultSetFactoryWrapper����������PagerCondition���擾����PagerResultSetWrapper�ɃZ�b�g���܂��B</li>
 * <li>PagerResultSetWrapper��PagerCondition�����Ɏw�肳�ꂽ�͈͂̌��ʃZ�b�g��Ԃ��܂��B</li>
 * <li>PagerResultSetWrapper��ResultSet�̑�������PagerCondition�ɃZ�b�g���܂��B</li>
 * </ol>
 * 
 * @author Toshitaka Agata(Nulab,inc.)
 * @see PagerContext#pushArgs(Object[])
 * @see PagerResultSetFactoryWrapper#createResultSet(PreparedStatement)
 * @see PagerResultSetWrapper#next()
 */
public class PagerS2DaoInterceptorWrapper extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    /** �I���W�i����S2DaoInterceptor */
    private S2DaoInterceptor interceptor_;

    /**
     * �R���X�g���N�^
     * 
     * @param interceptor
     *            �I���W�i����S2DaoInterceptor
     */
    public PagerS2DaoInterceptorWrapper(S2DaoInterceptor interceptor) {
        this.interceptor_ = interceptor;
    }

    /**
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            PagerContext.getContext().pushArgs(invocation.getArguments());
            return interceptor_.invoke(invocation);
        } finally {
            PagerContext.getContext().popArgs();
        }
    }
}