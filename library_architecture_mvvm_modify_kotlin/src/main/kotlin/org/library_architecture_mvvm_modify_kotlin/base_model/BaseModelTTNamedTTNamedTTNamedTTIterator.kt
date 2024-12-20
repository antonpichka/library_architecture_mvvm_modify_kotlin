package org.library_architecture_mvvm_modify_kotlin.base_model

import org.library_architecture_mvvm_modify_kotlin.CurrentModelWIndex

abstract class BaseModelTTNamedTTNamedTTNamedTTIterator protected constructor() {
    private val listModelIterator: MutableList<BaseModel> = mutableListOf()

    protected abstract fun currentModelWIndex(): CurrentModelWIndex

    fun getSortedListModelFromListModelParameterListModelIterator(listModel: List<BaseModel>): List<BaseModel> {
        if(listModel.isEmpty()) {
            return mutableListOf()
        }
        listModelIterator.addAll(listModel)
        val newListModel = mutableListOf<BaseModel>()
        while(listModelIterator.isNotEmpty()) {
            val currentModelWIndex = currentModelWIndex()
            listModelIterator.removeAt(currentModelWIndex.index)
            newListModel.add(currentModelWIndex.currentModel)
        }
        return newListModel
    }

    @Suppress("UNCHECKED_CAST")
    protected fun<T : BaseModel> listModelIterator(): MutableList<T> {
        return listModelIterator as MutableList<T>
    }
}