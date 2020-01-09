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

public class IonDictionaryItemImpl extends ASTWrapperPsiElement implements IonDictionaryItem {

  public IonDictionaryItemImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull IonVisitor visitor) {
    visitor.visitDictionaryItem(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof IonVisitor) accept((IonVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public IonSimpleValue getSimpleValue() {
    return findNotNullChildByClass(IonSimpleValue.class);
  }

}
