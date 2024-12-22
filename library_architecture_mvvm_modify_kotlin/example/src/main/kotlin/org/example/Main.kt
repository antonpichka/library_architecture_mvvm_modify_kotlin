package org.example

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.timeout
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import org.library_architecture_mvvm_modify_kotlin.ExceptionController
import org.library_architecture_mvvm_modify_kotlin.TempCacheProvider
import org.library_architecture_mvvm_modify_kotlin.base_data_for_named.BaseDataForNamed
import org.library_architecture_mvvm_modify_kotlin.base_exception.EnumGuilty
import org.library_architecture_mvvm_modify_kotlin.base_exception.LocalException
import org.library_architecture_mvvm_modify_kotlin.base_exception.NetworkException
import org.library_architecture_mvvm_modify_kotlin.base_model.BaseListModel
import org.library_architecture_mvvm_modify_kotlin.base_model.BaseListModelWrapper
import org.library_architecture_mvvm_modify_kotlin.base_model.BaseModel
import org.library_architecture_mvvm_modify_kotlin.base_model.BaseModelWrapper
import org.library_architecture_mvvm_modify_kotlin.base_model_wrapper_repository.BaseModelWrapperRepository
import org.library_architecture_mvvm_modify_kotlin.base_named_stream_w_state.BaseNamedStreamWState
import org.library_architecture_mvvm_modify_kotlin.base_named_stream_w_state.DefaultStreamWState
import org.library_architecture_mvvm_modify_kotlin.debugPrint
import org.library_architecture_mvvm_modify_kotlin.result.ResultWithModelWrapper

class FactoryModelWrapperRepositoryUtility private constructor() {
    companion object {
        fun getIPAddressWrapperRepositoryFromNamedKtorHttpClientService(namedKtorHttpClientService: BaseNamedKtorHttpClientService): IPAddressWrapperRepository {
            return IPAddressWrapperRepository(namedKtorHttpClientService)
        }
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
            listModel.add(IPAddress(itemListObject[0] as String))
        }
        return ListIPAddress(listModel) as Y
    }
}

abstract class BaseNamedKtorHttpClient protected constructor() {
    abstract suspend fun get(url: String, headers: Map<String,String>? = null): HttpResponse
    abstract suspend fun post(url: String, headers: Map<String,String>? = null, body: Any): HttpResponse
    abstract suspend fun close()
}

class DefaultKtorHttpClient(private val httpClient: HttpClient) : BaseNamedKtorHttpClient() {
    override suspend fun get(url: String, headers: Map<String,String>?): HttpResponse {
        if(headers == null) {
            return httpClient.get(url)
        }
        return httpClient.get(url) {
            headers {
                for(entry: Map.Entry<String, String> in headers.entries) {
                    append(entry.key,entry.value)
                }
            }
        }
    }

    override suspend fun post(url: String, headers: Map<String, String>?, body: Any): HttpResponse {
        if(headers == null) {
            return httpClient.post(url) {
                setBody(body)
            }
        }
        return httpClient.post(url) {
            headers {
                for(entry: Map.Entry<String, String> in headers.entries) {
                    append(entry.key,entry.value)
                }
            }
            setBody(body)
        }
    }

    override suspend fun close() {
        httpClient.close()
    }
}

class TimeoutKtorHttpClient(private val httpClient: HttpClient, private val timeout: Long) : BaseNamedKtorHttpClient() {
    override suspend fun get(url: String, headers: Map<String,String>?): HttpResponse {
        if(headers == null) {
            return httpClient.get(url)
        }
        return httpClient.get(url) {
            headers {
                for(entry: Map.Entry<String, String> in headers.entries) {
                    append(entry.key,entry.value)
                }
            }
            timeout {
                requestTimeoutMillis = timeout
            }
        }
    }

    override suspend fun post(url: String, headers: Map<String, String>?, body: Any): HttpResponse {
        if(headers == null) {
            return httpClient.post(url) {
                setBody(body)
            }
        }
        return httpClient.post(url) {
            headers {
                for(entry: Map.Entry<String, String> in headers.entries) {
                    append(entry.key,entry.value)
                }
            }
            timeout {
                requestTimeoutMillis = timeout
            }
            setBody(body)
        }
    }

    override suspend fun close() {
        httpClient.close()
    }
}

abstract class BaseNamedKtorHttpClientService protected constructor() {
    abstract fun getParameterNamedKtorHttpClient(): BaseNamedKtorHttpClient?
}

class DefaultKtorHttpClientService private constructor() : BaseNamedKtorHttpClientService() {
    companion object {
        val instance = DefaultKtorHttpClientService()
    }

    private var namedKtorHttpClient: BaseNamedKtorHttpClient? = null

    override fun getParameterNamedKtorHttpClient(): BaseNamedKtorHttpClient? {
        if(namedKtorHttpClient != null) {
            return namedKtorHttpClient
        }
        namedKtorHttpClient = DefaultKtorHttpClient(HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        })
        return namedKtorHttpClient
    }
}

class TimeoutKtorHttpClientService private constructor() : BaseNamedKtorHttpClientService() {
    companion object {
        val instance = TimeoutKtorHttpClientService()
    }

    private var namedKtorHttpClient: BaseNamedKtorHttpClient? = null

    override fun getParameterNamedKtorHttpClient(): BaseNamedKtorHttpClient? {
        if(namedKtorHttpClient != null) {
            return namedKtorHttpClient
        }
        namedKtorHttpClient = TimeoutKtorHttpClient(HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }, 5000)
        return namedKtorHttpClient
    }
}

open class IPAddressWrapperRepository(protected val namedKtorHttpClientService: BaseNamedKtorHttpClientService) : BaseModelWrapperRepository() {
    override fun dispose() {
    }

    suspend fun getIPAddressParameterNamedKtorHttpClientService(): ResultWithModelWrapper {
        return try {
            withContext(Dispatchers.IO) {
                val response: HttpResponse? = async {
                    namedKtorHttpClientService
                        .getParameterNamedKtorHttpClient()
                        ?.get(KeysUrlEndpointUtility.JSONIP_API)
                }.await()
                if (response?.status?.value != 200) {
                    throw NetworkException.fromKeyAndStatusCode(
                        IPAddressWrapperRepository::class.java,
                        response?.status.toString(),
                        response?.status?.value ?: 0
                    )
                }
                val jsonElement: JsonElement = response.body<JsonElement>()
                val data: Map<String, Any> = jsonElement.jsonObject.mapValues {
                    it.value.toString()
                }
                val ipByIPAddress = getSafeValueFromMapAndKeyAndDefaultValue(
                    data,
                    KeysHttpClientServiceUtility.IP_ADDRESS_QQ_IP,
                    ""
                )
                return@withContext ResultWithModelWrapper.success(IPAddressWrapper(listOf(ipByIPAddress)))
            }
        } catch (e: NetworkException) {
            return ResultWithModelWrapper.exception(e)
        } catch (e: Exception) {
            return ResultWithModelWrapper.exception(LocalException(
                IPAddressWrapperRepository::class.java, EnumGuilty.DEVICE, ReadyDataUtility.UNKNOWN, e.stackTraceToString())
            )
        }
    }
}

enum class EnumDataForMainVM {
    IS_LOADING,
    EXCEPTION,
    SUCCESS
}

class DataForMainVM(isLoading: Boolean, var iPAddress: IPAddress) : BaseDataForNamed<EnumDataForMainVM>(isLoading) {
    override fun getEnumDataForNamed(): EnumDataForMainVM {
        if (isLoading) {
            return EnumDataForMainVM.IS_LOADING
        }
        if (exceptionController.isWhereNotEqualsNullParameterException()) {
            return EnumDataForMainVM.EXCEPTION
        }
        return EnumDataForMainVM.SUCCESS
    }

    override fun toString(): String {
        return "DataForMainVM(isLoading: $isLoading, " +
                "exceptionController: $exceptionController, " +
                "iPAddress: $iPAddress)"
    }
}

class MainVM {
    // ModelWrapperRepository
    private val iPAddressWrapperRepository = FactoryModelWrapperRepositoryUtility.getIPAddressWrapperRepositoryFromNamedKtorHttpClientService(TimeoutKtorHttpClientService.instance)

    // TempCacheProvider
    private val tempCacheProvider = TempCacheProvider()

    // NamedUtility

    // NamedStreamWState
    private val namedStreamWState: BaseNamedStreamWState<EnumDataForMainVM,DataForMainVM> =
        DefaultStreamWState(DataForMainVM(true, IPAddress("")))

    fun init() = runBlocking {
        namedStreamWState.listenStreamDataForNamedFromCallback { _ ->
            build()
        }
        val firstRequest = async { firstRequest() }.await()
        debugPrint("MainVM: $firstRequest")
        namedStreamWState.notifyStreamDataForNamed()
    }

    fun dispose() {
        iPAddressWrapperRepository.dispose()
        tempCacheProvider.dispose(emptyList())
        namedStreamWState.dispose()
    }

    private fun build() {
        val dataWNamed = namedStreamWState.getDataForNamed()
        when(dataWNamed.getEnumDataForNamed()) {
            EnumDataForMainVM.IS_LOADING -> debugPrint("Build: IsLoading")
            EnumDataForMainVM.EXCEPTION -> debugPrint("Build: Exception(${dataWNamed.exceptionController.getKeyParameterException()})")
            EnumDataForMainVM.SUCCESS -> debugPrint("Build: Success(${dataWNamed.iPAddress})")
        }
    }

    private fun firstRequest(): String = runBlocking {
        val getIPAddressParameterNamedKtorHttpClientService = async { iPAddressWrapperRepository.getIPAddressParameterNamedKtorHttpClientService() }.await()
        if(getIPAddressParameterNamedKtorHttpClientService.exceptionController.isWhereNotEqualsNullParameterException()) {
            return@runBlocking firstQQFirstRequestQQGetIPAddressParameterNamedHttpClientService(getIPAddressParameterNamedKtorHttpClientService.exceptionController)
        }
        namedStreamWState.getDataForNamed().isLoading = false
        namedStreamWState.getDataForNamed().iPAddress = getIPAddressParameterNamedKtorHttpClientService.modelWrapper!!.createModel()
        return@runBlocking ReadyDataUtility.SUCCESS
    }

    private fun firstQQFirstRequestQQGetIPAddressParameterNamedHttpClientService(exceptionController: ExceptionController): String {
        namedStreamWState.getDataForNamed().isLoading = false
        namedStreamWState.getDataForNamed().exceptionController = exceptionController
        return exceptionController.getKeyParameterException()
    }
}

fun main() = runBlocking {
    val mainVM = MainVM()
    async { mainVM.init() }.await()
    mainVM.dispose()
}
// EXPECTED OUTPUT:
//
// MainVM: success
// Build: Success(IPAddress(ip: ${your_ip}))
//
// Process finished with exit code 0

/// OR

// EXPECTED OUTPUT:
//
// ===start_to_trace_exception===
//
// WhereHappenedException(Class) --> ${WhereHappenedException(Class)}
// NameException(Class) --> ${NameException(Class)}
// toString() --> ${toString()}
//
// ===end_to_trace_exception===
//
// MainVM: ${getKeyParameterException}
// Build: Exception(${getKeyParameterException})
//
// Process finished with exit code 0