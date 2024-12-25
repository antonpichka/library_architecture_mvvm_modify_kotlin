package io.github.antonpichka.lamm.base_data_for_named

import io.github.antonpichka.lamm.ExceptionController

abstract class BaseDataForNamed<T : Enum<T>> protected constructor(
    var isLoading: Boolean)
{
    var exceptionController: ExceptionController = ExceptionController.success()

    abstract fun getEnumDataForNamed(): T

    abstract override fun toString(): String
}