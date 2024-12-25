package io.github.antonpichka.lamm.named_test_main

import io.github.antonpichka.lamm.TempCacheProvider
import io.github.antonpichka.lamm.named_service.IterationService
import org.junit.Assert.assertEquals
import org.junit.Test

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