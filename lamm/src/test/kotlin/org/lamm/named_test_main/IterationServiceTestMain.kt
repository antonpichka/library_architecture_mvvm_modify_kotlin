package org.lamm.named_test_main

import org.junit.Assert.assertEquals
import org.junit.Test
import org.lamm.TempCacheProvider
import org.lamm.named_service.IterationService

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