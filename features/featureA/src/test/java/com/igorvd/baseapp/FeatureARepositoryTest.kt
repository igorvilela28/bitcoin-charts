package com.igorvd.baseapp

import com.igorvd.baseapp.features.featureA.FeatureARepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FeatureARepositoryTest {

    private lateinit var dataRepository: FeatureARepository

    @Before
    fun setup() {
        dataRepository = FeatureARepository()
    }

    @Test
    fun `should get hello world message`() {
        Assert.assertEquals("hello world", dataRepository.helloWorld())
    }
}