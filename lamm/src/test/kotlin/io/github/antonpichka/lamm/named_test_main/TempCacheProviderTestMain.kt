package io.github.antonpichka.lamm.named_test_main

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import io.github.antonpichka.lamm.TempCacheProvider

class TempCacheProviderTestMain {
    @Test
    fun tempCacheProviderTestMain() = runBlocking {
        val tempCacheProvider = TempCacheProvider()
        val key = "key"
        val keyFirst = tempCacheProvider.getNamed(key,"default")
        var resultCallback = ""
        var resultCallbackFirst = ""
        tempCacheProvider.listenNamed(key) { event ->
            resultCallback = event as String
        }
        delay(1000)
        tempCacheProvider.updateWNotify(key, "Two")
        tempCacheProvider.dispose(listOf(key))
        tempCacheProvider.listenNamed(key) { event ->
            resultCallbackFirst = event as String
        }
        delay(1000)
        tempCacheProvider.updateWNotify(key,"Three")
        assertEquals("default", keyFirst)
        assertEquals("Two",resultCallback)
        assertEquals("Three",resultCallbackFirst)
    }
}