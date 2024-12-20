package org.library_architecture_mvvm_modify_kotlin.base_named_stream_w_state

import org.library_architecture_mvvm_modify_kotlin.IDispose
import org.library_architecture_mvvm_modify_kotlin.base_data_for_named.BaseDataForNamed

abstract class BaseNamedStreamWState<T : Enum<T>, Y : BaseDataForNamed<T>> protected constructor() :
    IDispose {
    abstract fun getDataForNamed(): Y

    abstract fun listenStreamDataForNamedFromCallback(callback: (event: Y) -> Unit)

    abstract fun notifyStreamDataForNamed()
}