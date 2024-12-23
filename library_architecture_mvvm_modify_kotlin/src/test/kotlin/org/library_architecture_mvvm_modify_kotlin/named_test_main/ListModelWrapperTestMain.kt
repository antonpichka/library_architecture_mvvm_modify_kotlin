package org.library_architecture_mvvm_modify_kotlin.named_test_main

import org.junit.Assert.assertEquals
import org.junit.Test
import org.library_architecture_mvvm_modify_kotlin.base_model.BaseListModel
import org.library_architecture_mvvm_modify_kotlin.base_model.BaseListModelWrapper
import org.library_architecture_mvvm_modify_kotlin.base_model.BaseModel

open class IPAddress(val ip: String) : BaseModel(ip) {
    override fun clone(): IPAddress {
        return IPAddress(ip)
    }

    override fun toString(): String {
        return "IPAddress(ip: $ip)"
    }
}

open class ListIPAddress<T : IPAddress>(listModel: MutableList<T>) : BaseListModel<T>(listModel) {
    @Suppress("UNCHECKED_CAST")
    override fun clone(): ListIPAddress<T> {
        val newListModel = mutableListOf<T>()
        for(itemModel: T in listModel) {
            newListModel.add(itemModel.clone() as T)
        }
        return ListIPAddress(newListModel)
    }

    override fun toString(): String {
        var strListModel = "\n"
        for(itemModel: T in listModel) {
            strListModel += "$itemModel,\n"
        }
        return "ListIPAddress(listModel: [$strListModel])"
    }
}

open class ListIPAddressWrapper(listsListObject: List<List<Any>>) : BaseListModelWrapper(listsListObject) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : BaseModel, Y : BaseListModel<T>> createListModel(): Y {
        val listModel = mutableListOf<IPAddress>()
        for(itemListObject: List<Any> in listsListObject) {
            for(itemObject: Any in itemListObject) {
                listModel.add(IPAddress(itemObject as String))
            }
        }
        return ListIPAddress(listModel) as Y
    }
}
class ListModelWrapperTestMain {
    @Test
    fun listModelWrapperTestMain() {
        val listIPAddressWrapper = ListIPAddressWrapper(
            listOf(
                listOf("124.suka"),
                listOf("124.pidar"),
                listOf("124.zakompleksovanie"),
                listOf("124.5_rubley_v_karmane_net_na_rabotu_pizduyte"),
                listOf("124.hah_musor"),
                listOf("124.ez viebal"),
                listOf("124.sosat+lejat")
            )
        )
        val listIPAddress: ListIPAddress<IPAddress> = listIPAddressWrapper.createListModel()
        assertEquals(listIPAddress.listModel.size,7)
    }
}