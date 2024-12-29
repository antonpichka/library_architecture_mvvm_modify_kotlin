package io.github.antonpichka.lamm.base_model

abstract class BaseListModel<T : BaseModel> protected constructor(val listModel: MutableList<T>) {
    abstract fun clone(): BaseListModel<T>

    abstract override fun toString(): String

    fun sortingFromModelTTNamedTTNamedTTNamedTTIteratorParameterListModel(modelTTNamedTTNamedTTNamedTTIterator: BaseModelTTNamedTTNamedTTNamedTTIterator<T>) {
        val sortedListModelFromListModelParameterListModelIterator = modelTTNamedTTNamedTTNamedTTIterator.getSortedListModelFromListModelParameterListModelIterator(listModel)
        listModel
            .takeIf { it.isNotEmpty() }
            ?.clear()
        sortedListModelFromListModelParameterListModelIterator
            .takeIf { it.isNotEmpty() }
            .let { listModel.addAll(sortedListModelFromListModelParameterListModelIterator) }
    }

    fun insertFromNewModelParameterListModel(newModel: T) {
        listModel.add(newModel)
    }

    fun updateFromNewModelParameterListModel(newModel: T) {
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

    fun insertListFromNewListModelParameterListModel(newListModel: List<T>) {
        listModel.addAll(newListModel)
    }

    fun updateListFromNewListModelParameterListModel(newListModel: List<T>) {
        for(newItemModel: T in newListModel) {
            val indexOfFirst = listModel.indexOfFirst { it.uniqueId == newItemModel.uniqueId }
            if(indexOfFirst == -1) {
                continue
            }
            listModel[indexOfFirst] = newItemModel
        }
    }

    fun deleteListFromListUniqueIdByModelParameterListModel(listUniqueIdByModel: List<String>) {
        for(itemUniqueIdByModel: String in listUniqueIdByModel) {
            val indexOfFirst = listModel.indexOfFirst { it.uniqueId == itemUniqueIdByModel }
            if(indexOfFirst == -1) {
                continue
            }
            listModel.removeAt(indexOfFirst)
        }
    }
}