package io.github.luke_biel

import com.intellij.lexer.FlexAdapter
import java.io.Reader

class IonLexerAdapter : FlexAdapter(IonLexer(null as Reader?))