package io.github.antonpichka.lamm.base_model_wrapper_repository

import io.github.antonpichka.lamm.IDispose

abstract class BaseModelWrapperRepository protected constructor() : IDispose {
    protected fun getSafeValueFromMapAndKeyAndDefaultValue(map: Map<String,Any>, key: String, defaultValue: Any): Any {
        try {
            if(map.containsKey(key)) {
                return map[key] ?: ""
            }
            return defaultValue
        } catch (_: Exception) {
            return defaultValue
        }
    }
}