package io.github.luke_biel.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import io.github.luke_biel.language.IonFileType
import io.github.luke_biel.language.IonLanguage

class IonFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, IonLanguage.INSTANCE) {
    override fun getFileType(): FileType {
        return IonFileType.INSTANCE
    }

    override fun toString(): String {
        return "Ion File"
    }
}
