package io.github.luke_biel.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.luke_biel.psi.IonElement


abstract class IonElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), IonElement