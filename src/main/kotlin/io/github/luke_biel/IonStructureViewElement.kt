package io.github.luke_biel

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import io.github.luke_biel.psi.IonFile
import io.github.luke_biel.psi.IonHeaderItem
import java.util.*

// TODO: I need to receive NavigatablePsiElement here or some IonItem or something
// Look into JsonElement which is interface implemented by JsonFile AND ""JsonTreeElements""
class IonStructureViewElement(private val element: PsiElement) : StructureViewTreeElement,
    SortableTreeElement {
    override fun getValue(): Any {
        return element
    }

    override fun navigate(requestFocus: Boolean) {
//        element.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean {
//        return element.canNavigate()
        return false
    }

    override fun canNavigateToSource(): Boolean {
//        return element.canNavigateToSource()
        return false
    }

    override fun getAlphaSortKey(): String {
//        val name = element.name
        return element.text
    }

    override fun getPresentation(): ItemPresentation {
//        val presentation = element.presentation
        return PresentationData(element.text, "", element.getIcon(0), null)
    }

    override fun getChildren(): Array<TreeElement> {
        return if (element is IonFile) {
            val items: Array<IonHeaderItem> =
                PsiTreeUtil.getChildrenOfType(element, IonHeaderItem::class.java)!!
            val treeElements: MutableList<TreeElement> =
                ArrayList(items.size)
            for (item in items) {
                treeElements.add(IonStructureViewElement(item))
            }
            treeElements.toTypedArray()
        } else {
            emptyArray()
        }
    }
}
