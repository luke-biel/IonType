package io.github.luke_biel

import com.intellij.lexer.FlexAdapter
import io.github.luke_biel.IonLexer
import java.io.Reader

class IonLexerAdapter : FlexAdapter(IonLexer(null as Reader?))