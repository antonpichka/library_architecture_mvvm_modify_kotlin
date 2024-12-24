package org.lamm.named_service

import org.lamm.base_exception.EnumGuilty
import org.lamm.base_exception.LocalException

class TempCacheService private constructor() {
    companion object {
        val instance = TempCacheService()
    }

    private val tempCache: MutableMap<String,Any> = mutableMapOf()
    private val tempCacheWStreams: MutableMap<String, MutableMap<Int, (event: Any) -> Unit>> = mutableMapOf()

    fun <T> getNamed(keyTempCache: String, defaultValue: T): T {
        if(!tempCache.containsKey(keyTempCache)) {
            return defaultValue
        }
        @Suppress("UNCHECKED_CAST")
        return tempCache[keyTempCache] as T
    }

    fun dispose(listKeyTempCache: List<String>, iteration: Int) {
        for(itemKeyTempCache: String in listKeyTempCache) {
           if(!tempCacheWStreams.containsKey(itemKeyTempCache)) {
               return
           }
           tempCacheWStreams[itemKeyTempCache]?.remove(iteration)
        }
    }

    fun listenNamed(keyTempCache: String, callback: (event: Any) -> Unit, iteration: Int) {
        if(!tempCacheWStreams.containsKey(keyTempCache)) {
            tempCacheWStreams[keyTempCache] = mutableMapOf()
            tempCacheWStreams[keyTempCache]?.set(iteration, callback)
            return
        }
        if((tempCacheWStreams[keyTempCache] ?: mutableMapOf()).containsKey(iteration)) {
            throw LocalException(
                this,
                EnumGuilty.DEVELOPER,
                "{ $keyTempCache---$iteration }",
                "Under such a key and iteration there already exists a listener: { $keyTempCache---$iteration }")
        }
        tempCacheWStreams[keyTempCache]?.set(iteration, callback)
    }

    fun update(keyTempCache: String, value: Any) {
        tempCache[keyTempCache] = value
    }

    fun updateWNotify(keyTempCache: String, value: Any) {
        update(keyTempCache,value)
        if(!tempCacheWStreams.containsKey(keyTempCache)) {
            return
        }
        for(entry: MutableMap.MutableEntry<Int, (event: Any) -> Unit> in tempCacheWStreams[keyTempCache]?.entries ?: mutableSetOf()) {
            entry.value(value)
        }
    }

    fun delete(keyTempCache: String) {
        tempCache.remove(keyTempCache)
        tempCacheWStreams.remove(keyTempCache)
    }
}