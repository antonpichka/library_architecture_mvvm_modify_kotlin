package org.library_architecture_mvvm_modify_kotlin.base_data_for_named

import org.library_architecture_mvvm_modify_kotlin.ExceptionController

abstract class BaseDataForNamed<T : Enum<T>> protected constructor(
    var isLoading: Boolean)
{
    var exceptionController: ExceptionController = ExceptionController.success()

    abstract fun getEnumDataForNamed(): T

    abstract override fun toString(): String
}