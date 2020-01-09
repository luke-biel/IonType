package io.github.luke_biel

import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.util.IconLoader

import javax.swing.*

class IonFileType : LanguageFileType(IonLanguage.INSTANCE) {
    companion object {
        @Suppress("unused") // Is used via plugin config
        @JvmField
        val INSTANCE = IonFileType()
    }

    override fun getIcon(): Icon? {
        return IconLoader.getIcon("/icons/icon.png")
    }

    override fun getName(): String {
        return "Ion file"
    }

    override fun getDefaultExtension(): String {
        return "ion"
    }

    override fun getDescription(): String {
        return "Anixe .ion files"
    }

}

class IonLanguage : Language("ION") {
    companion object {
        @Suppress("unused") // Is used via plugin config
        @JvmField
        val INSTANCE = IonLanguage()
    }
}
