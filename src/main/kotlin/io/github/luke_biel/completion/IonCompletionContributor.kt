package io.github.luke_biel.completion

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns
import io.github.luke_biel.language.IonLanguage

class IonCompletionContributor : CompletionContributor() {
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement().withLanguage(IonLanguage.INSTANCE),
            IonCompletionProvider.INSTANCE
        )
    }
}