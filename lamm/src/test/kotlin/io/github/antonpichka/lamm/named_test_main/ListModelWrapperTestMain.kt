package io.github.antonpichka.lamm.named_test_main

import org.junit.Assert.assertEquals
import org.junit.Test
import io.github.antonpichka.lamm.base_model.BaseListModel
import io.github.antonpichka.lamm.base_model.BaseListModelWrapper
import io.github.antonpichka.lamm.base_model.BaseModel
import io.github.antonpichka.lamm.base_model.BaseModelWrapper

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

open class IPAddressWrapper(listObject: List<Any>) : BaseModelWrapper(listObject) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : BaseModel> createModel(): T {
        return IPAddress(listObject[0] as String) as T
    }
}

open class ListIPAddressWrapper(listsListObject: List<List<Any>>) : BaseListModelWrapper(listsListObject) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : BaseModel, Y : BaseListModel<T>> createListModel(): Y {
        val listModel = mutableListOf<IPAddress>()
        for(itemListObject: List<Any> in listsListObject) {
            val iPAddressWrapper = IPAddressWrapper(itemListObject)
            listModel.add(iPAddressWrapper.createModel())
        }
        return ListIPAddress(listModel) as Y
    }
}
class ListModelWrapperTestMain {
    @Test
    fun listModelWrapperTestMain() {
        val listIPAddressWrapper =
            ListIPAddressWrapper(
                listOf(
                    listOf("1.1.1.1"),
                    listOf("2.2.2.2"),
                    listOf("3.3.3.3"),
                    listOf("4.4.4.4"),
                    listOf("5.5.5.5"),
                    listOf("6.6.6.6"),
                    listOf("7.7.7.7")
                )
            )
        val listIPAddress: ListIPAddress<IPAddress> = listIPAddressWrapper.createListModel()
        assertEquals(listIPAddress.listModel.size,7)
    }
}