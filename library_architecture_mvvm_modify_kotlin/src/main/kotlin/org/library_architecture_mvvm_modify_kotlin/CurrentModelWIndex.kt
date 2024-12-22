package org.library_architecture_mvvm_modify_kotlin

import org.library_architecture_mvvm_modify_kotlin.base_model.BaseModel

class CurrentModelWIndex<T : BaseModel>(
    val currentModel: T,
    val index: Int)