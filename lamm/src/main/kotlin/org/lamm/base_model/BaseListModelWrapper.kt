package org.lamm.base_model

abstract class BaseListModelWrapper protected constructor(protected val listsListObject: List<List<Any>>) {
    abstract fun<T : BaseModel, Y : BaseListModel<T>> createListModel(): Y
}