package org.lamm.result

import org.lamm.ExceptionController
import org.lamm.base_exception.BaseException
import org.lamm.base_model.BaseListModelWrapper

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