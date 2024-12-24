package org.lamm.base_named_state

import org.lamm.IDispose
import org.lamm.base_data_for_named.BaseDataForNamed

abstract class BaseNamedState<T : Enum<T>, Y : BaseDataForNamed<T>> protected constructor() : IDispose {
    abstract fun getDataForNamed(): Y
}