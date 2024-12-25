package io.github.antonpichka.lamm.base_named_state

import io.github.antonpichka.lamm.IDispose
import io.github.antonpichka.lamm.base_data_for_named.BaseDataForNamed

abstract class BaseNamedState<T : Enum<T>, Y : BaseDataForNamed<T>> protected constructor() :
    IDispose {
    abstract fun getDataForNamed(): Y
}