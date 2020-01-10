package io.github.luke_biel.psi.impl

import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import io.github.luke_biel.psi.IonElementFactory
import io.github.luke_biel.psi.IonItem
import io.github.luke_biel.psi.IonTypes
import javax.swing.Icon


public class IonPsiImplUtil {
    companion object {
        @JvmStatic
        fun getPresentation(element: IonItem): ItemPresentation? {
            return object : ItemPresentation {
                override fun getPresentableText(): String? {
                    return element.text
                }

                override fun getLocationString(): String? {
                    return element.containingFile.name
                }

                override fun getIcon(unused: Boolean): Icon? {
                    return null // TODO: Get some icon
                }
            }
        }

        @JvmStatic
        fun getNameIdentifier(element: IonItem): PsiElement? {
            return element.node.findChildByType(IonTypes.HEADER)?.psi
        }

        @JvmStatic
        fun getName(element: IonItem?): String? {
            return element?.name
        }

        @JvmStatic
        fun setName(element: IonItem, newName: String?): PsiElement? {
            val keyNode: ASTNode? = element.node.findChildByType(IonTypes.HEADER)
            if (keyNode != null) {
                val item: IonItem = IonElementFactory.createItem(element.project, newName ?: "item")
                val newKeyNode: ASTNode = item.firstChild.node
                element.node.replaceChild(keyNode, newKeyNode)
            }
            return element
        }
    }
}