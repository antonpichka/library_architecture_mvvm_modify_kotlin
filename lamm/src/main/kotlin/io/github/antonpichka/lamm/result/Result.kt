package io.github.antonpichka.lamm.result

import io.github.antonpichka.lamm.ExceptionController
import io.github.antonpichka.lamm.base_exception.BaseException

class Result private constructor(
    val parameter: Any?,
    val exceptionController: ExceptionController
) {
    companion object {
        fun success(parameter: Any): Result {
            return Result(parameter, ExceptionController.success())
        }

        fun exception(exception: BaseException): Result {
            return Result(
                null,
                ExceptionController.exception(exception)
            )
        }
    }
}