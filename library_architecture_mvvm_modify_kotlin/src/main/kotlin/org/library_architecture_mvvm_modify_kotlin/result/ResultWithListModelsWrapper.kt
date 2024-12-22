package org.library_architecture_mvvm_modify_kotlin.result

import org.library_architecture_mvvm_modify_kotlin.ExceptionController
import org.library_architecture_mvvm_modify_kotlin.base_exception.BaseException
import org.library_architecture_mvvm_modify_kotlin.base_model.BaseListModelWrapper

class ResultWithListModelsWrapper private constructor(
    val listModelWrapper: BaseListModelWrapper?,
    val exceptionController: ExceptionController
) {
    companion object {
        fun success(listModelWrapper: BaseListModelWrapper): ResultWithListModelsWrapper {
            return ResultWithListModelsWrapper(listModelWrapper, ExceptionController.success())
        }

        fun exception(exception: BaseException): ResultWithListModelsWrapper {
            return ResultWithListModelsWrapper(null, ExceptionController.exception(exception))
        }
    }
}