package org.library_architecture_mvvm_modify_kotlin.result

import org.library_architecture_mvvm_modify_kotlin.ExceptionController
import org.library_architecture_mvvm_modify_kotlin.base_exception.BaseException
import org.library_architecture_mvvm_modify_kotlin.base_model.BaseListModelWrapper

class ResultWithListModelsWrapper<T : BaseListModelWrapper> private constructor(
    val listModelWrapper: T?,
    val exceptionController: ExceptionController
)
{
    companion object {
        fun success(listModelWrapper: BaseListModelWrapper): ResultWithListModelsWrapper<BaseListModelWrapper> {
            return ResultWithListModelsWrapper(listModelWrapper, ExceptionController.success())
        }

        fun exception(exception: BaseException): ResultWithListModelsWrapper<BaseListModelWrapper> {
            return ResultWithListModelsWrapper(null, ExceptionController.exception(exception))
        }
    }
}