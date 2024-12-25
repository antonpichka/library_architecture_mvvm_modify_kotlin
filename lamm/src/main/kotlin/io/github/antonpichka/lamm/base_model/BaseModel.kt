package io.github.antonpichka.lamm.base_model

abstract class BaseModel protected constructor(val uniqueId: String) {
    abstract fun clone(): BaseModel

    abstract override fun toString(): String
}