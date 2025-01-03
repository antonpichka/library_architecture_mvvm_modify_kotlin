package io.github.antonpichka.lamm.base_exception

class LocalException(
    thisClass: Any,
    val enumGuilty: EnumGuilty,
    key: String,
    val exceptionMessage: String = "") : BaseException(thisClass, "LocalException", key)
{
    init {
        debugPrintExceptionParametersThisClassAndExceptionClass()
    }

    override fun toString(): String {
        return "LocalException(enumGuilty: ${enumGuilty.name}, " +
                "key: ${key}, " +
                "exceptionMessage (optional): ${exceptionMessage})"
    }
}