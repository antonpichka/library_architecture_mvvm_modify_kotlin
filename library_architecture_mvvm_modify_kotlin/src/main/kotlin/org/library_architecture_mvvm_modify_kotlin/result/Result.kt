package org.library_architecture_mvvm_modify_kotlin.result

import org.library_architecture_mvvm_modify_kotlin.ExceptionController
import org.library_architecture_mvvm_modify_kotlin.base_exception.BaseException

class Result<T : Any> private constructor(
    val parameter: T?,
    val exceptionController: ExceptionController
)
{
    companion object {
        fun success(parameter: Any): Result<Any> {
            return Result(parameter, ExceptionController.success())
        }

        fun exception(exception: BaseException): Result<Any> {
            return Result(null, ExceptionController.exception(exception))
        }
    }
}