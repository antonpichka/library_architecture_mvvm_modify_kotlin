package io.github.antonpichka.lamm

import io.github.antonpichka.lamm.base_model.BaseModel

class CurrentModelWIndex<T : BaseModel>(
    val currentModel: T,
    val index: Int)