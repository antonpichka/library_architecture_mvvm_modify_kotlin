package io.github.antonpichka.lamm.result

import io.github.antonpichka.lamm.ExceptionController
import io.github.antonpichka.lamm.base_exception.BaseException
import io.github.antonpichka.lamm.base_model.BaseModelWrapper

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