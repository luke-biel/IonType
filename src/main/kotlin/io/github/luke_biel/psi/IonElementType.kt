package io.github.luke_biel.psi

import com.intellij.psi.tree.IElementType
import io.github.luke_biel.language.IonLanguage

class IonElementType(debugName: String) : IElementType(debugName, IonLanguage.INSTANCE)