/*
 * Copyright (c) 2018-present, Jim Kynde Meyer
 * All rights reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.intellij.lang.jsgraphql.ide.rename;

import com.intellij.lang.jsgraphql.ide.project.GraphQLPsiSearchHelper;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

/**
 * Prevents renaming of elements in the built-in schema
 */
public class GraphQLRenameVetoCondition implements Condition<PsiElement> {
    @Override
    public boolean value(PsiElement psiElement) {
        final PsiFile builtInSchema = GraphQLPsiSearchHelper.getService(psiElement.getProject()).getBuiltInSchema();
        return builtInSchema == psiElement.getContainingFile();
    }
}