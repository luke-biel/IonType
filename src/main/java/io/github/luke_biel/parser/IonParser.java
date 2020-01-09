// This is a generated file. Not intended for manual editing.
package io.github.luke_biel.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static io.github.luke_biel.psi.IonTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class IonParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return ionFile(b, l + 1);
  }

  /* ********************************************************** */
  // (SQUARE_OPN simple_value (COLLECTION_SEP simple_value)* SQUARE_CLS) | (SQUARE_OPN SQUARE_CLS)
  public static boolean array(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array")) return false;
    if (!nextTokenIs(b, SQUARE_OPN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = array_0(b, l + 1);
    if (!r) r = array_1(b, l + 1);
    exit_section_(b, m, ARRAY, r);
    return r;
  }

  // SQUARE_OPN simple_value (COLLECTION_SEP simple_value)* SQUARE_CLS
  private static boolean array_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SQUARE_OPN);
    r = r && simple_value(b, l + 1);
    r = r && array_0_2(b, l + 1);
    r = r && consumeToken(b, SQUARE_CLS);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COLLECTION_SEP simple_value)*
  private static boolean array_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!array_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "array_0_2", c)) break;
    }
    return true;
  }

  // COLLECTION_SEP simple_value
  private static boolean array_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLLECTION_SEP);
    r = r && simple_value(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SQUARE_OPN SQUARE_CLS
  private static boolean array_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SQUARE_OPN, SQUARE_CLS);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // CSV_SEP (csv_values? CSV_SEP)+
  public static boolean csv(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "csv")) return false;
    if (!nextTokenIs(b, CSV_SEP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CSV_SEP);
    r = r && csv_1(b, l + 1);
    exit_section_(b, m, CSV, r);
    return r;
  }

  // (csv_values? CSV_SEP)+
  private static boolean csv_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "csv_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = csv_1_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!csv_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "csv_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // csv_values? CSV_SEP
  private static boolean csv_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "csv_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = csv_1_0_0(b, l + 1);
    r = r && consumeToken(b, CSV_SEP);
    exit_section_(b, m, null, r);
    return r;
  }

  // csv_values?
  private static boolean csv_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "csv_1_0_0")) return false;
    csv_values(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // CSV_KEY CSV_ASSIGN csv_simple_value
  public static boolean csv_dictionary_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "csv_dictionary_item")) return false;
    if (!nextTokenIs(b, CSV_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, CSV_KEY, CSV_ASSIGN);
    r = r && csv_simple_value(b, l + 1);
    exit_section_(b, m, CSV_DICTIONARY_ITEM, r);
    return r;
  }

  /* ********************************************************** */
  // STRING|INTEGER|BOOLEAN|REAL|RANGE|array
  public static boolean csv_simple_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "csv_simple_value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CSV_SIMPLE_VALUE, "<csv simple value>");
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, INTEGER);
    if (!r) r = consumeToken(b, BOOLEAN);
    if (!r) r = consumeToken(b, REAL);
    if (!r) r = consumeToken(b, RANGE);
    if (!r) r = array(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // simple_value | csv_dictionary_item | CSV_OTHER
  public static boolean csv_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "csv_value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CSV_VALUE, "<csv value>");
    r = simple_value(b, l + 1);
    if (!r) r = csv_dictionary_item(b, l + 1);
    if (!r) r = consumeToken(b, CSV_OTHER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // csv_value+ | CSV_FILLER
  public static boolean csv_values(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "csv_values")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CSV_VALUES, "<csv values>");
    r = csv_values_0(b, l + 1);
    if (!r) r = consumeToken(b, CSV_FILLER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // csv_value+
  private static boolean csv_values_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "csv_values_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = csv_value(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!csv_value(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "csv_values_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (CURLY_OPN dictionary_item (COLLECTION_SEP dictionary_item)* CURLY_CLS) | (CURLY_OPN CURLY_CLS)
  public static boolean dictionary(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionary")) return false;
    if (!nextTokenIs(b, CURLY_OPN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = dictionary_0(b, l + 1);
    if (!r) r = dictionary_1(b, l + 1);
    exit_section_(b, m, DICTIONARY, r);
    return r;
  }

  // CURLY_OPN dictionary_item (COLLECTION_SEP dictionary_item)* CURLY_CLS
  private static boolean dictionary_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionary_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CURLY_OPN);
    r = r && dictionary_item(b, l + 1);
    r = r && dictionary_0_2(b, l + 1);
    r = r && consumeToken(b, CURLY_CLS);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COLLECTION_SEP dictionary_item)*
  private static boolean dictionary_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionary_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!dictionary_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dictionary_0_2", c)) break;
    }
    return true;
  }

  // COLLECTION_SEP dictionary_item
  private static boolean dictionary_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionary_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLLECTION_SEP);
    r = r && dictionary_item(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // CURLY_OPN CURLY_CLS
  private static boolean dictionary_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionary_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, CURLY_OPN, CURLY_CLS);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // KEY ASSIGN simple_value
  public static boolean dictionary_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionary_item")) return false;
    if (!nextTokenIs(b, KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEY, ASSIGN);
    r = r && simple_value(b, l + 1);
    exit_section_(b, m, DICTIONARY_ITEM, r);
    return r;
  }

  /* ********************************************************** */
  // HEADER
  public static boolean header_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "header_item")) return false;
    if (!nextTokenIs(b, HEADER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HEADER);
    exit_section_(b, m, HEADER_ITEM, r);
    return r;
  }

  /* ********************************************************** */
  // item*
  static boolean ionFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ionFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ionFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // header_item|csv|property|COMMENT
  static boolean item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item")) return false;
    boolean r;
    r = header_item(b, l + 1);
    if (!r) r = csv(b, l + 1);
    if (!r) r = property(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  /* ********************************************************** */
  // simple_value | array | dictionary
  public static boolean kvc_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "kvc_value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, KVC_VALUE, "<kvc value>");
    r = simple_value(b, l + 1);
    if (!r) r = array(b, l + 1);
    if (!r) r = dictionary(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KEY ASSIGN kvc_value
  public static boolean property(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property")) return false;
    if (!nextTokenIs(b, KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEY, ASSIGN);
    r = r && kvc_value(b, l + 1);
    exit_section_(b, m, PROPERTY, r);
    return r;
  }

  /* ********************************************************** */
  // STRING|INTEGER|BOOLEAN|REAL|RANGE
  public static boolean simple_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SIMPLE_VALUE, "<simple value>");
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, INTEGER);
    if (!r) r = consumeToken(b, BOOLEAN);
    if (!r) r = consumeToken(b, REAL);
    if (!r) r = consumeToken(b, RANGE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
