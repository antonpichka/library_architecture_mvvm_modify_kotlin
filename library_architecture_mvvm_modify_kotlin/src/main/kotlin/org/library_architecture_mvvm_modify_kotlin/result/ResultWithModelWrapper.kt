package org.library_architecture_mvvm_modify_kotlin.result

import org.library_architecture_mvvm_modify_kotlin.ExceptionController
import org.library_architecture_mvvm_modify_kotlin.base_exception.BaseException
import org.library_architecture_mvvm_modify_kotlin.base_model.BaseModelWrapper

class ResultWithModelWrapper private constructor(
    val modelWrapper: BaseModelWrapper?,
    val exceptionController: ExceptionController
) {
    companion object {
        fun success(modelWrapper: BaseModelWrapper): ResultWithModelWrapper {
            return ResultWithModelWrapper(modelWrapper, ExceptionController.success())
        }

        fun exception(exception: BaseException): ResultWithModelWrapper {
            return ResultWithModelWrapper(null, ExceptionController.exception(exception))
        }
    }
}