package org.example

import io.ktor.client.statement.HttpResponse
import org.library_architecture_mvvm_modify_kotlin.base_model.*

class FactoryModelWrapperRepositoryUtility private constructor() {
    companion object {
    }
}

class KeysUrlEndpointUtility private constructor() {
    companion object {
        /* JsonipAPI */
        const val JSONIP_API: String = "https://jsonip.com";
        const val JSONIP_API_QQ_PROVIDERS: String = "$JSONIP_API/providers";
    }
}

class ReadyDataUtility private constructor() {
    companion object {
        const val UNKNOWN: String = "unknown"
        const val SUCCESS: String = "success"
    }
}

class KeysHttpClientServiceUtility private constructor() {
    companion object {
        /* IPAddress */
        const val IP_ADDRESS_QQ_IP = "ip"
    }
}

open class IPAddress(val ip: String) : BaseModel(ip) {
    override fun clone(): IPAddress {
        return IPAddress(ip)
    }

    override fun toString(): String {
        return "IPAddress(ip: $ip)"
    }
}

open class ListIPAddress(listModel: MutableList<BaseModel>) : BaseListModel(listModel) {
    override fun clone(): ListIPAddress {
        val newListModel = mutableListOf<BaseModel>()
        for(itemModel: IPAddress in listModel<IPAddress>()) {
            newListModel.add(itemModel.clone())
        }
        return ListIPAddress(newListModel)
    }

    override fun toString(): String {
        var strListModel = "\n"
        for(itemModel: IPAddress in listModel<IPAddress>()) {
            strListModel += "$itemModel,\n"
        }
        return "ListIPAddress(listModel: [$strListModel])"
    }
}

open class IPAddressWrapper(listObject: List<Any>) : BaseModelWrapper(listObject) {
    override fun createModel(): IPAddress {
        return IPAddress(listObject[0] as String)
    }
}

open class ListIPAddressWrapper(listsListObject: List<List<Any>>) : BaseListModelWrapper(listsListObject) {
    override fun createListModel(): ListIPAddress {
        val listModel = mutableListOf<BaseModel>()
        for(itemListObject: List<Any> in listsListObject) {
            listModel.add(IPAddress(itemListObject[0] as String))
        }
        return ListIPAddress(listModel)
    }
}

abstract class BaseNamedKtorHttpClient {
    abstract fun get(url: String, headers: Map<String, String>?): HttpResponse
    abstract fun post(url: String, headers: Map<String, String>?, body: Any): HttpResponse
    abstract fun close()
}

fun main() {
}