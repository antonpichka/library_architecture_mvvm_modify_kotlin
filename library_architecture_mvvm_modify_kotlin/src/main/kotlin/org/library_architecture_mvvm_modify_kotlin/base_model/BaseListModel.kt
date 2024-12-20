package org.library_architecture_mvvm_modify_kotlin.base_model

abstract class BaseListModel protected constructor(private val listModel: MutableList<BaseModel>) {
    abstract fun clone(): BaseListModel

    abstract override fun toString(): String

    @Suppress("UNCHECKED_CAST")
    fun<T : BaseModel> listModel(): MutableList<T> {
        return listModel as MutableList<T>
    }

    fun sortingFromModelTTNamedTTNamedTTNamedTTIteratorParameterListModel(modelTTNamedTTNamedTTNamedTTIterator: BaseModelTTNamedTTNamedTTNamedTTIterator) {
        val sortedListModelFromListModelParameterListModelIterator = modelTTNamedTTNamedTTNamedTTIterator.getSortedListModelFromListModelParameterListModelIterator(listModel)
        listModel
            .takeIf { it.isNotEmpty() }
            ?.clear()
        sortedListModelFromListModelParameterListModelIterator
            .takeIf { it.isNotEmpty() }
            .let { listModel.addAll(sortedListModelFromListModelParameterListModelIterator) }
    }

    fun insertFromNewModelParameterListModel(newModel: BaseModel) {
        listModel.add(newModel)
    }

    fun updateFromNewModelParameterListModel(newModel: BaseModel) {
        val indexOfFirst = listModel.indexOfFirst { it.uniqueId == newModel.uniqueId }
        if(indexOfFirst == -1) {
            return
        }
        listModel[indexOfFirst] = newModel
    }

    fun deleteFromUniqueIdByModelParameterListModel(uniqueIdByModel: String) {
        val indexOfFirst = listModel.indexOfFirst { it.uniqueId == uniqueIdByModel }
        if(indexOfFirst == -1) {
            return
        }
        listModel.removeAt(indexOfFirst)
    }

    fun insertListFromNewListModelParameterListModel(newListModel: List<BaseModel>) {
        listModel.addAll(newListModel)
    }

    fun updateListFromNewListModelParameterListModel(newListModel: List<BaseModel>) {
        for(newItemModel: BaseModel in newListModel) {
            val indexOfFirst = listModel.indexOfFirst { it.uniqueId == newItemModel.uniqueId }
            if(indexOfFirst == -1) {
                continue
            }
            listModel[indexOfFirst] = newItemModel
        }
    }

    fun deleteListFromNewListModelParameterListModel(newListModel: List<BaseModel>) {
        for(newItemModel: BaseModel in newListModel) {
            val indexOfFirst = listModel.indexOfFirst { it.uniqueId == newItemModel.uniqueId }
            if(indexOfFirst == -1) {
                continue
            }
            listModel.removeAt(indexOfFirst)
        }
    }
}