package org.lamm.result

import org.lamm.ExceptionController
import org.lamm.base_exception.BaseException
import org.lamm.base_model.BaseModelWrapper

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