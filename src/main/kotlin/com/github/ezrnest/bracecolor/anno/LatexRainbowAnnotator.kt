package com.github.ezrnest.bracecolor.anno

import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import nl.hannahsten.texifyidea.file.LatexFile
import nl.hannahsten.texifyidea.psi.*  // 导入 TeXiFy PSI
import java.util.*

class LatexRainbowAnnotator : Annotator {

    class RainbowVisitor(val holder: AnnotationHolder) : LatexPsiRecursiveWalker(Int.MAX_VALUE) {

        val stack = Stack<PsiElement>()  // 栈存储开括号和层级

        override fun elementStart(e: PsiElement) {
            when (e.elementType) {
                LatexTypes.OPEN_BRACE,
                LatexTypes.OPEN_PAREN,
                LatexTypes.OPEN_BRACKET
                    -> visitOpenBrace(e)

                LatexTypes.CLOSE_BRACE,
                LatexTypes.CLOSE_PAREN,
                LatexTypes.CLOSE_BRACKET
                    -> visitCloseBrace(e)
            }
        }

        fun visitOpenBrace(e: PsiElement) {
            stack.push(e)
        }

        fun visitCloseBrace(e: PsiElement) {
            if (stack.isEmpty()) return
            val openBrace = stack.pop()
            val level = stack.size + 1  // 当前层级为栈大小 + 1
            val colorKey = LatexRainbowColors.getColorAttributes(level)

            // 高亮开括号
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(openBrace.textRange)
                .enforcedTextAttributes(colorKey)
                .create()

            // 高亮闭括号
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(e.textRange)
                .enforcedTextAttributes(colorKey)
                .create()
        }
    }


    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element !is LatexFile) return  // 只处理 LaTeX 内容
        element.accept(RainbowVisitor(holder))
    }
}