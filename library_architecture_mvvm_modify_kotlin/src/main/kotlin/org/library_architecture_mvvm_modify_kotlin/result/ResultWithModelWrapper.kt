package org.library_architecture_mvvm_modify_kotlin.result

import org.library_architecture_mvvm_modify_kotlin.ExceptionController
import org.library_architecture_mvvm_modify_kotlin.base_exception.BaseException
import org.library_architecture_mvvm_modify_kotlin.base_model.BaseModelWrapper

class ResultWithModelWrapper<T : BaseModelWrapper> private constructor(
    val modelWrapper: T?,
    val exceptionController: ExceptionController
)
{
    companion object {
        fun success(modelWrapper: BaseModelWrapper): ResultWithModelWrapper<BaseModelWrapper> {
            return ResultWithModelWrapper(modelWrapper, ExceptionController.success())
        }

        fun exception(exception: BaseException): ResultWithModelWrapper<BaseModelWrapper> {
            return ResultWithModelWrapper(null, ExceptionController.exception(exception))
        }
    }
}