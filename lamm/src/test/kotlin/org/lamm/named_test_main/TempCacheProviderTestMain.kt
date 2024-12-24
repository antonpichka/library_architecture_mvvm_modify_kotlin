package org.lamm.named_test_main

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.lamm.TempCacheProvider

class TempCacheProviderTestMain {
    @Test
    fun tempCacheProviderTestMain() = runBlocking {
        val tempCacheProvider = TempCacheProvider()
        val key = "key"
        val keyFirst = tempCacheProvider.getNamed(key,"default")
        var resultCallbackFirst = ""
        var resultCallbackSecond = ""
        tempCacheProvider.listenNamed(key) { event ->
            resultCallbackFirst = event as String
        }
        delay(1000)
        tempCacheProvider.updateWNotify(key, "Two")
        tempCacheProvider.dispose(listOf(key))
        tempCacheProvider.listenNamed(key) { event ->
            resultCallbackSecond = event as String
        }
        delay(1000)
        tempCacheProvider.updateWNotify(key,"Three")
        assertEquals("default", keyFirst)
        assertEquals("Two",resultCallbackFirst)
        assertEquals("Three",resultCallbackSecond)
    }
}