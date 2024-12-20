package org.library_architecture_mvvm_modify_kotlin.base_named_state

import org.library_architecture_mvvm_modify_kotlin.IDispose
import org.library_architecture_mvvm_modify_kotlin.base_data_for_named.BaseDataForNamed

abstract class BaseNamedState<T : Enum<T>, Y : BaseDataForNamed<T>> protected constructor() :
    IDispose {
    abstract fun getDataForNamed(): Y
}