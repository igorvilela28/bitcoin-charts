package com.igorvd.baseapp.core.data

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DataRepositoryTest {

    private lateinit var dataRepository: DataRepository

    @Before
    fun setup() {
        dataRepository = DataRepository()
    }

    @Test
    fun `should get hello world message`() {
        Assert.assertEquals("Hello world from data!", dataRepository.helloWorld())
    }
}