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

import java.util.Stack;

/**
 * �y�[�W���̏����X���b�h���[�J���ɕێ����܂��B
 * 
 * @author Toshitaka Agata(Nulab,inc.)
 */
class PagerContext {

    private static final Object[] EMPTY_ARGS = new Object[0];

    /** �X���b�h���[�J�� */
    private static ThreadLocal threadLocal = new ThreadLocal() {
        protected Object initialValue() {
            return new PagerContext();
        }
    };

    /** Stack */
    private Stack argsStack = new Stack();

    /**
     * �R���X�g���N�^
     */
    private PagerContext() {
    };

    /**
     * ���݂̃X���b�h�Ɍ��т���PagerContext���擾���܂��B
     * 
     * @return PagerContext
     */
    public static PagerContext getContext() {
        return (PagerContext) threadLocal.get();
    }

    public void pushArgs(Object[] args) {
        argsStack.push(args);
    }

    public Object[] popArgs() {
        return (Object[]) argsStack.pop();
    }

    public Object[] peekArgs() {
        if (argsStack.size() == 0) {
            return EMPTY_ARGS;
        } else {
            return (Object[]) argsStack.peek();
        }
    }

    /**
     * ���\�b�h�̈�����PagerCondition���܂܂�Ă��邩�ǂ����𔻒肵�܂��B
     * 
     * @param args
     *            ����
     * @return true/false
     */
    public static boolean isPagerCondition(Object[] args) {
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg instanceof PagerCondition) {
                return true;
            }
        }
        return false;
    }

    /**
     * ���\�b�h�̈�������PagerCondition���擾���܂��B
     * 
     * @param args
     *            ����
     * @return PagerCondition
     */
    public static PagerCondition getPagerCondition(Object[] args) {
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg instanceof PagerCondition) {
                return (PagerCondition) arg;
            }
        }
        return null;
    }

}
