package io.github.luke_biel.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import io.github.luke_biel.language.IonFileType


object IonElementFactory {
    fun createItem(project: Project?, name: String): IonItem {
        val file: IonFile = createFile(project, name)
        return file.firstChild as IonItem
    }

    private fun createFile(project: Project?, text: String): IonFile {
        return PsiFileFactory.getInstance(project).createFileFromText("dummy.ion", IonFileType.INSTANCE, text) as IonFile
    }
}