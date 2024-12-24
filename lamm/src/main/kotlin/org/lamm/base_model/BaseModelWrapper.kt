package org.lamm.base_model

abstract class BaseModelWrapper protected constructor(protected val listObject: List<Any>) {
    abstract fun<T : BaseModel> createModel(): T
}