package com.github.ezrnest.bracecolor

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class MyPluginTest : BasePlatformTestCase() {

    fun testNothing(){

    }

    override fun getTestDataPath() = "src/test/testData/rename"
}
