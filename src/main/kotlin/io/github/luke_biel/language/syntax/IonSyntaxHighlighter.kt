package io.github.luke_biel.language.syntax

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import io.github.luke_biel.IonLexerAdapter
import io.github.luke_biel.psi.IonTypes


class IonSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer {
        return IonLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            IonTypes.KEY, IonTypes.CSV_KEY -> KEY_KEYS
            IonTypes.STRING, IonTypes.CSV_OTHER -> STRING_KEYS
            IonTypes.BOOLEAN -> SYMBOL_KEYS
            IonTypes.REAL, IonTypes.INTEGER, IonTypes.RANGE -> NUMBER_KEYS
            IonTypes.HEADER -> HEADER_KEYS
            IonTypes.COMMENT -> COMMENT_KEYS
            TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
            else -> {
                EMPTY_KEYS
            }
        }
    }

    companion object {
        val KEY =
            TextAttributesKey.createTextAttributesKey(
                "ION_DICTIONARY_KEY",
                DefaultLanguageHighlighterColors.IDENTIFIER
            )
        val SYMBOL =
            TextAttributesKey.createTextAttributesKey(
                "ION_SYMBOL",
                DefaultLanguageHighlighterColors.KEYWORD
            )
        val STRING =
            TextAttributesKey.createTextAttributesKey(
                "ION_STRING",
                DefaultLanguageHighlighterColors.STRING
            )
        val NUMBER =
            TextAttributesKey.createTextAttributesKey(
                "ION_NUMBER",
                DefaultLanguageHighlighterColors.NUMBER
            )
        val HEADER =
            TextAttributesKey.createTextAttributesKey(
                "ION_HEADER",
                DefaultLanguageHighlighterColors.CLASS_NAME
            )
        val COMMENT =
            TextAttributesKey.createTextAttributesKey(
                "ION_COMMENT",
                DefaultLanguageHighlighterColors.LINE_COMMENT
            )
        val BAD_CHARACTER =
            TextAttributesKey.createTextAttributesKey(
                "ION_BAD_CHARACTER",
                HighlighterColors.BAD_CHARACTER
            )
        private val KEY_KEYS =
            arrayOf(KEY)
        private val SYMBOL_KEYS =
            arrayOf(SYMBOL)
        private val STRING_KEYS =
            arrayOf(STRING)
        private val NUMBER_KEYS =
            arrayOf(NUMBER)
        private val HEADER_KEYS =
            arrayOf(HEADER)
        private val COMMENT_KEYS =
            arrayOf(COMMENT)
        private val BAD_CHAR_KEYS =
            arrayOf(BAD_CHARACTER)
        private val EMPTY_KEYS =
            arrayOf<TextAttributesKey>()
    }
}