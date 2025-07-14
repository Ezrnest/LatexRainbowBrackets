package com.github.ezrnest.bracecolor.anno


import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.ui.JBColor
import java.awt.Color

object LatexRainbowColors {
    // 定义一组好看的自定义颜色键，轮换使用（7种彩虹渐变）

    val COLORS = listOf(
        JBColor(Color(135, 206, 250), Color(70, 130, 180)), // 天空蓝
        JBColor(Color(255, 228, 181), Color(255, 160, 122)), // 浅橙色
        JBColor(Color(221, 160, 221), Color(238, 130, 238)),// 紫色
        JBColor(Color(255, 165, 0), Color(255, 140, 0)), // 橙色
        JBColor(Color(255, 105, 180), Color(255, 20, 147)), // 粉红色
        JBColor(Color(144, 238, 144), Color(34, 139, 34)), // 浅绿色
        JBColor(Color(220, 220, 0), Color(170, 170, 1)), // 黄色
        JBColor(Color(75, 0, 130), Color(138, 43, 226)), // 靛蓝和紫色
    )

    val COLOR_ATTRIBUTES = COLORS.map {
        TextAttributes().apply {
            foregroundColor = it
            // 可以添加其他属性，如粗体或背景色，如果需要
            // effectType = EffectType.BOLD_LINE_UNDERSCORE  // 示例：添加下划线效果
        }
    }

    fun getColorAttributes(level: Int): TextAttributes {
        // 根据层级获取对应的颜色属性键
        val index = level % COLORS.size
        return COLOR_ATTRIBUTES[index]
    }

}