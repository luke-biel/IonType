// This is a generated file. Not intended for manual editing.
package io.github.luke_biel.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.github.luke_biel.psi.IonTypes.*;
import io.github.luke_biel.psi.*;
import com.intellij.navigation.ItemPresentation;

public class IonItemImpl extends IonElementImpl implements IonItem {

  public IonItemImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull IonVisitor visitor) {
    visitor.visitItem(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof IonVisitor) accept((IonVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public IonCsv getCsv() {
    return findChildByClass(IonCsv.class);
  }

  @Override
  @Nullable
  public IonHeaderItem getHeaderItem() {
    return findChildByClass(IonHeaderItem.class);
  }

  @Override
  @Nullable
  public IonProperty getProperty() {
    return findChildByClass(IonProperty.class);
  }

  @Override
  @Nullable
  public String getName() {
    return IonPsiImplUtil.getName(this);
  }

  @Override
  @Nullable
  public PsiElement setName(@Nullable String newName) {
    return IonPsiImplUtil.setName(this, newName);
  }

  @Override
  @Nullable
  public ItemPresentation getPresentation() {
    return IonPsiImplUtil.getPresentation(this);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return IonPsiImplUtil.getNameIdentifier(this);
  }

}
