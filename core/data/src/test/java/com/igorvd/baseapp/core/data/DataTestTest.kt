package com.igorvd.baseapp.core.data

import org.junit.Assert
import org.junit.Test

class DataTestTest {

    @Test
    fun `should have correct hello world string`() {
        Assert.assertEquals("Hello world from data!", DataTest.helloWorld)
    }


}