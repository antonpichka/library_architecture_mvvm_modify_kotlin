package org.library_architecture_mvvm_modify_kotlin.base_exception

import org.library_architecture_mvvm_modify_kotlin.debugPrintException

abstract class BaseException protected constructor(
    private val thisClass: Any,
    private val exceptionClass: String,
    val key: String) : Exception()
{
    override fun toString(): String {
        throw Exception("Needs extends and must return type 'String'")
    }

    /// Call this method in the descendant constructor as the last line
    protected fun debugPrintExceptionParametersThisClassAndExceptionClass() {
        debugPrintException("\n===start_to_trace_exception===\n")
        debugPrintException("WhereHappenedException(Class) --> $thisClass\n" +
                "NameException(Class) --> $exceptionClass\n" +
                "toString() --> ${toString()}")
        debugPrintException("\n===end_to_trace_exception===\n")
    }
}