package org.library_architecture_mvvm_modify_kotlin.named_test_main

import org.junit.Assert.assertEquals
import org.junit.Test
import org.library_architecture_mvvm_modify_kotlin.TempCacheProvider
import org.library_architecture_mvvm_modify_kotlin.named_service.IterationService

class IterationServiceTestMain    {
    @Test
    fun iterationServiceTestMain() {
        TempCacheProvider()
        TempCacheProvider()
        TempCacheProvider()
        TempCacheProvider()
        TempCacheProvider()
        assertEquals(5, IterationService.instance.newValueParameterIteration())
    }
}