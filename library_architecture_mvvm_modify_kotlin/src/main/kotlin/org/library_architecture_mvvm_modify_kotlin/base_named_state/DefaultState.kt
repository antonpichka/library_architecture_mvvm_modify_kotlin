package org.library_architecture_mvvm_modify_kotlin.base_named_state

import org.library_architecture_mvvm_modify_kotlin.base_data_for_named.BaseDataForNamed

class DefaultState<T : Enum<T>, Y : BaseDataForNamed<T>>(
    private val dataForNamed: Y) : BaseNamedState<T, Y>()
{
    override fun getDataForNamed(): Y {
        return dataForNamed
    }

    override fun dispose() {
    }
}