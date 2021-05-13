package com.igorvd.baseapp

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AppRepositoryTest {

    private lateinit var dataRepository: AppRepository

    @Before
    fun setup() {
        dataRepository = AppRepository()
    }

    @Test
    fun `should get hello world message`() {
        Assert.assertEquals("hello world", dataRepository.helloWorld())
    }
}