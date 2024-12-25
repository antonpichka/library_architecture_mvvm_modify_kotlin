package io.github.antonpichka.lamm

import io.github.antonpichka.lamm.base_exception.BaseException

class ExceptionController private constructor(
    private val exception: BaseException?)
{
    companion object {
        fun success(): ExceptionController {
            return ExceptionController(null)
        }

        fun exception(exception: BaseException): ExceptionController {
            return ExceptionController(exception)
        }
    }

    override fun toString(): String {
        if(exception == null) {
            return "ExceptionController(exception: null)"
        }
        return "ExceptionController(exception: $exception)"
    }

    fun getKeyParameterException(): String {
        return exception?.key ?: ""
    }

    fun isWhereNotEqualsNullParameterException(): Boolean {
        return exception != null
    }
}