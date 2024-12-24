package org.lamm

import org.lamm.base_model.BaseModel

class CurrentModelWIndex<T : BaseModel>(
    val currentModel: T,
    val index: Int)