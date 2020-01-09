package io.github.luke_biel;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import io.github.luke_biel.psi.IonTypes;
import com.intellij.psi.TokenType;

%%

%class IonLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE=[\ \t\f]

// snake case keys
FIRST_KEY_CHARACTER=[a-zA-Z_]
KEY_CHARACTER=[a-zA-Z0-9_]

ASSIGN==
COLLECTION_SEP=,
CSV_ASSIGN=:
FILL=-

STRING_DELIMITER=\"
STRING_CHARACTER=[^\n\f\"]
BOOLEAN=(true|false)
INTEGER_CHARACTER=[0-9]
FLOAT_DELIMITER=\.

SQUARE_OPN=\[
SQUARE_CLS=\]
CURLY_OPN=\{
CURLY_CLS=\}

// headers use UPPER.CASE with dot (.) separators
FIRST_HEADER_CHARACTER=[A-Z_]
HEADER_CHARACTER=[A-Z\._]

NEG=-?
PERCENT=%?

CSV_SEP=\|

%s ION_CSV
%s CSV_VAL
%s W_CSV_ASSIGN

%%

#[^\n]*$                                                                                    { return IonTypes.COMMENT; }
<YYINITIAL> ^{CSV_SEP}{FILL}*({CSV_SEP}{FILL}*)*{CSV_SEP}$                                  { yybegin(YYINITIAL); return IonTypes.CSV_FILLER; }
<YYINITIAL,ION_CSV> {INTEGER_CHARACTER}+{CSV_ASSIGN}{INTEGER_CHARACTER}+                    { return IonTypes.RANGE; }
<ION_CSV> {FIRST_KEY_CHARACTER}{KEY_CHARACTER}*/{CSV_ASSIGN}                                { yybegin(W_CSV_ASSIGN); return IonTypes.CSV_KEY; }
<W_CSV_ASSIGN> {CSV_ASSIGN}                                                                 { yybegin(CSV_VAL); return IonTypes.CSV_ASSIGN; }
<CSV_VAL> {INTEGER_CHARACTER}+{CSV_ASSIGN}{INTEGER_CHARACTER}+                              { yybegin(ION_CSV); return IonTypes.RANGE; }
<CSV_VAL> {BOOLEAN}                                                                         { yybegin(ION_CSV); return IonTypes.BOOLEAN; }
<CSV_VAL> {PERCENT}{NEG}{INTEGER_CHARACTER}*{FLOAT_DELIMITER}{INTEGER_CHARACTER}+           { yybegin(ION_CSV); return IonTypes.REAL; }
<CSV_VAL> {PERCENT}{NEG}{INTEGER_CHARACTER}+                                                { yybegin(ION_CSV); return IonTypes.INTEGER; }
<YYINITIAL,ION_CSV> {SQUARE_OPN}{FIRST_HEADER_CHARACTER}{HEADER_CHARACTER}*{SQUARE_CLS}     { return IonTypes.HEADER; }
<YYINITIAL,ION_CSV> {SQUARE_OPN}                                                            { return IonTypes.SQUARE_OPN; }
<YYINITIAL,ION_CSV> {SQUARE_CLS}                                                            { return IonTypes.SQUARE_CLS; }
<YYINITIAL,ION_CSV> {ASSIGN}                                                                { return IonTypes.ASSIGN; }
<YYINITIAL,ION_CSV> {CSV_SEP}$                                                              { yybegin(YYINITIAL); return IonTypes.CSV_SEP; }
<YYINITIAL,ION_CSV> {CSV_SEP}                                                               { yybegin(ION_CSV); return IonTypes.CSV_SEP; }
<YYINITIAL,ION_CSV> {COLLECTION_SEP}                                                        { return IonTypes.COLLECTION_SEP; }
<YYINITIAL,ION_CSV> {CURLY_OPN}                                                             { return IonTypes.CURLY_OPN; }
<YYINITIAL,ION_CSV> {CURLY_CLS}                                                             { return IonTypes.CURLY_CLS; }
<YYINITIAL,ION_CSV> {BOOLEAN}                                                               { return IonTypes.BOOLEAN; }
<YYINITIAL> {FIRST_KEY_CHARACTER}{KEY_CHARACTER}*                                           { return IonTypes.KEY; }
<YYINITIAL,ION_CSV> {PERCENT}{NEG}{INTEGER_CHARACTER}*{FLOAT_DELIMITER}{INTEGER_CHARACTER}+ { return IonTypes.REAL; }
<YYINITIAL,ION_CSV> {PERCENT}{NEG}{INTEGER_CHARACTER}+                                      { return IonTypes.INTEGER; }
<YYINITIAL,ION_CSV> {STRING_DELIMITER}{STRING_CHARACTER}*{STRING_DELIMITER}                 { return IonTypes.STRING; }
<CSV_VAL> {STRING_DELIMITER}{STRING_CHARACTER}*{STRING_DELIMITER}                           { yybegin(ION_CSV); return IonTypes.STRING; }
<ION_CSV> [^ \n\|]+                                                                         { return IonTypes.CSV_OTHER; }

\n                      { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
({CRLF}|{WHITE_SPACE})+ { return TokenType.WHITE_SPACE; }
[^]                     { return TokenType.BAD_CHARACTER; }
