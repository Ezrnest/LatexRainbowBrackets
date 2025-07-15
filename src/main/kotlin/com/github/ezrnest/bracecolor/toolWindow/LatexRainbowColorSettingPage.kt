package com.github.ezrnest.bracecolor.toolWindow


import com.github.ezrnest.bracecolor.MyRainbowColors
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.editor.colors.TextAttributesKey
import nl.hannahsten.texifyidea.highlighting.LatexSyntaxHighlighter

class LatexColorSettingsPage : ColorSettingsPage {
    // 定义描述符数组，用于显示在设置页面
    private val ATTRIBUTES = MyRainbowColors.COLOR_ATTRIBUTES_KEYS.mapIndexed { i, it ->
        AttributesDescriptor("Rainbow Bracket Level ${i + 1}", it).apply {

        }
    }.toTypedArray()

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = ATTRIBUTES

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName(): String = "LaTeX Rainbow Brackets"

    override fun getIcon() = null  // 可以添加图标，如果有

    override fun getHighlighter() = LatexSyntaxHighlighter()

    override fun getDemoText(): String = buildString {
        appendLine("\\documentclass{article}")
        appendLine("\\begin{document}")
        for (i in MyRainbowColors.COLOR_ATTRIBUTES_KEYS.indices) {
            append("<rainbow$i>{</rainbow$i>${i+1} ")
        }
        append("...")
        for (i in MyRainbowColors.COLOR_ATTRIBUTES_KEYS.indices.reversed()) {
            append(" ${i+1}<rainbow$i>}</rainbow$i>")
        }
        appendLine()
        appendLine("\\end{document}")
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>{
        return MyRainbowColors.COLOR_ATTRIBUTES_KEYS.mapIndexed {
            index, key ->
            // 创建一个映射，将自定义标签与颜色属性键关联
            "rainbow$index" to key
        }.toMap()
    }
}