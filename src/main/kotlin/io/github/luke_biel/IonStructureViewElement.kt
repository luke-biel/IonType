package io.github.luke_biel

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import io.github.luke_biel.psi.IonElement
import io.github.luke_biel.psi.IonFile
import io.github.luke_biel.psi.IonHeaderItem
import io.github.luke_biel.psi.impl.IonElementImpl
import io.github.luke_biel.psi.impl.IonHeaderItemImpl
import java.util.*

class IonStructureViewElement(private val element: NavigatablePsiElement) : StructureViewTreeElement,
    SortableTreeElement {
    override fun getValue(): Any {
        return element
    }

    override fun navigate(requestFocus: Boolean) {
        element.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean {
        return element.canNavigate()
    }

    override fun canNavigateToSource(): Boolean {
        return element.canNavigateToSource()
    }

    override fun getAlphaSortKey(): String {
        val name = element.name
        return name ?: ""
    }

    override fun getPresentation(): ItemPresentation {
        val presentation = element.presentation
        return presentation ?: PresentationData()
    }

    override fun getChildren(): Array<TreeElement> {
        return if (element is IonFile) {
            val items: Array<IonElementImpl> =
                PsiTreeUtil.getChildrenOfType(element, IonElementImpl::class.java)!!
            val treeElements: MutableList<TreeElement> =
                ArrayList(items.size)
            for (item in items) {
                if (item.firstChild is IonHeaderItemImpl) {
                    treeElements.add(IonStructureViewElement(item))
                }
            }
            treeElements.toTypedArray()
        } else {
            emptyArray()
        }
    }
}
