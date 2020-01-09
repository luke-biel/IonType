// This is a generated file. Not intended for manual editing.
package io.github.luke_biel.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.github.luke_biel.psi.IonTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import io.github.luke_biel.psi.*;

public class IonKvcValueImpl extends ASTWrapperPsiElement implements IonKvcValue {

  public IonKvcValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull IonVisitor visitor) {
    visitor.visitKvcValue(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof IonVisitor) accept((IonVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public IonArray getArray() {
    return findChildByClass(IonArray.class);
  }

  @Override
  @Nullable
  public IonDictionary getDictionary() {
    return findChildByClass(IonDictionary.class);
  }

  @Override
  @Nullable
  public IonSimpleValue getSimpleValue() {
    return findChildByClass(IonSimpleValue.class);
  }

}
