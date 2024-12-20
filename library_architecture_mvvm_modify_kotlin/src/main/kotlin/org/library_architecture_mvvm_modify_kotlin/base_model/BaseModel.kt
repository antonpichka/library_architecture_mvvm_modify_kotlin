package org.library_architecture_mvvm_modify_kotlin.base_model

abstract class BaseModel protected constructor(val uniqueId: String) {
    abstract fun clone(): BaseModel

    abstract override fun toString(): String
}