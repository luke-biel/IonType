// This is a generated file. Not intended for manual editing.
package io.github.luke_biel.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import io.github.luke_biel.psi.impl.*;

public interface IonTypes {

  IElementType ARRAY = new IonElementType("ARRAY");
  IElementType CSV = new IonElementType("CSV");
  IElementType CSV_DICTIONARY_ITEM = new IonElementType("CSV_DICTIONARY_ITEM");
  IElementType CSV_SIMPLE_VALUE = new IonElementType("CSV_SIMPLE_VALUE");
  IElementType CSV_VALUE = new IonElementType("CSV_VALUE");
  IElementType CSV_VALUES = new IonElementType("CSV_VALUES");
  IElementType DICTIONARY = new IonElementType("DICTIONARY");
  IElementType DICTIONARY_ITEM = new IonElementType("DICTIONARY_ITEM");
  IElementType HEADER_ITEM = new IonElementType("HEADER_ITEM");
  IElementType KVC_VALUE = new IonElementType("KVC_VALUE");
  IElementType PROPERTY = new IonElementType("PROPERTY");
  IElementType SIMPLE_VALUE = new IonElementType("SIMPLE_VALUE");

  IElementType ASSIGN = new IonTokenType("ASSIGN");
  IElementType BOOLEAN = new IonTokenType("BOOLEAN");
  IElementType COLLECTION_SEP = new IonTokenType("COLLECTION_SEP");
  IElementType COMMENT = new IonTokenType("COMMENT");
  IElementType CSV_ASSIGN = new IonTokenType("CSV_ASSIGN");
  IElementType CSV_FILLER = new IonTokenType("CSV_FILLER");
  IElementType CSV_KEY = new IonTokenType("CSV_KEY");
  IElementType CSV_OTHER = new IonTokenType("CSV_OTHER");
  IElementType CSV_SEP = new IonTokenType("CSV_SEP");
  IElementType CURLY_CLS = new IonTokenType("CURLY_CLS");
  IElementType CURLY_OPN = new IonTokenType("CURLY_OPN");
  IElementType HEADER = new IonTokenType("HEADER");
  IElementType INTEGER = new IonTokenType("INTEGER");
  IElementType KEY = new IonTokenType("KEY");
  IElementType RANGE = new IonTokenType("RANGE");
  IElementType REAL = new IonTokenType("REAL");
  IElementType SQUARE_CLS = new IonTokenType("SQUARE_CLS");
  IElementType SQUARE_OPN = new IonTokenType("SQUARE_OPN");
  IElementType STRING = new IonTokenType("STRING");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ARRAY) {
        return new IonArrayImpl(node);
      }
      else if (type == CSV) {
        return new IonCsvImpl(node);
      }
      else if (type == CSV_DICTIONARY_ITEM) {
        return new IonCsvDictionaryItemImpl(node);
      }
      else if (type == CSV_SIMPLE_VALUE) {
        return new IonCsvSimpleValueImpl(node);
      }
      else if (type == CSV_VALUE) {
        return new IonCsvValueImpl(node);
      }
      else if (type == CSV_VALUES) {
        return new IonCsvValuesImpl(node);
      }
      else if (type == DICTIONARY) {
        return new IonDictionaryImpl(node);
      }
      else if (type == DICTIONARY_ITEM) {
        return new IonDictionaryItemImpl(node);
      }
      else if (type == HEADER_ITEM) {
        return new IonHeaderItemImpl(node);
      }
      else if (type == KVC_VALUE) {
        return new IonKvcValueImpl(node);
      }
      else if (type == PROPERTY) {
        return new IonPropertyImpl(node);
      }
      else if (type == SIMPLE_VALUE) {
        return new IonSimpleValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
