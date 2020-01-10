// This is a generated file. Not intended for manual editing.
package io.github.luke_biel.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface IonItem extends IonElement {

  @Nullable
  IonCsv getCsv();

  @Nullable
  IonHeaderItem getHeaderItem();

  @Nullable
  IonProperty getProperty();

  @Nullable
  String getName();

  @Nullable
  PsiElement setName(@Nullable String newName);

  @Nullable
  ItemPresentation getPresentation();

  @Nullable
  PsiElement getNameIdentifier();

}
