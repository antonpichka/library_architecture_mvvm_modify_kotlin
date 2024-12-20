package org.library_architecture_mvvm_modify_kotlin.named_test_main

import org.library_architecture_mvvm_modify_kotlin.CurrentModelWIndex
import org.library_architecture_mvvm_modify_kotlin.base_model.BaseListModel
import org.library_architecture_mvvm_modify_kotlin.base_model.BaseModel
import org.library_architecture_mvvm_modify_kotlin.base_model.BaseModelTTNamedTTNamedTTNamedTTIterator
import org.junit.Assert.assertEquals
import org.junit.Test

open class UserBalance(val username: String, val money: Int) : BaseModel(username) {
    override fun clone(): UserBalance {
        return UserBalance(username,money)
    }

    override fun toString(): String {
        return "UserBalance(username: $username, " +
                "money: $money)"
    }
}

open class ListUserBalance(listModel: MutableList<BaseModel>) : BaseListModel(listModel) {
    override fun clone(): ListUserBalance {
        val newListModel = mutableListOf<BaseModel>()
        for(itemModel: UserBalance in listModel<UserBalance>()) {
            newListModel.add(itemModel.clone())
        }
        return ListUserBalance(newListModel)
    }

    override fun toString(): String {
        var strListModel = "\n"
        for(itemModel: UserBalance in listModel<UserBalance>()) {
            strListModel += "$itemModel,\n"
        }
        return "ListUserBalance(listModel: [$strListModel])"
    }
}

class UserBalanceTTOrderByDescTTMoneyTTIterator : BaseModelTTNamedTTNamedTTNamedTTIterator() {
    override fun currentModelWIndex(): CurrentModelWIndex {
        var clone = listModelIterator<UserBalance>()[0].clone()
        if(listModelIterator<UserBalance>().size <= 1) {
            return CurrentModelWIndex(clone,0)
        }
        var indexRemove = 0
        for(i in 1 until listModelIterator<UserBalance>().size) {
            val itemModelIterator = listModelIterator<UserBalance>()[i]
            if(itemModelIterator.money > clone.money) {
                clone = itemModelIterator.clone()
                indexRemove = i
                continue
            }
        }
        return CurrentModelWIndex(clone, indexRemove)
    }
}

class ModelTTNamedTTNamedTTNamedTTIteratorTestMain {
    @Test
    fun modelTTNamedTTNamedTTNamedTTIteratorTestMain() {
        val listUserBalance = ListUserBalance(mutableListOf())
        listUserBalance.insertListFromNewListModelParameterListModel(
            listOf(
                UserBalance("Jone", 3),
                UserBalance("Freddy", 1),
                UserBalance("Mitsuya", 10),
                UserBalance("Duramichi", 5),
                UserBalance("Hook", 7),
                UserBalance("Sexy", -1)
            )
        )
        assertEquals("ListUserBalance(listModel: [\n" +
                "UserBalance(username: Jone, money: 3),\n" +
                "UserBalance(username: Freddy, money: 1),\n" +
                "UserBalance(username: Mitsuya, money: 10),\n" +
                "UserBalance(username: Duramichi, money: 5),\n" +
                "UserBalance(username: Hook, money: 7),\n" +
                "UserBalance(username: Sexy, money: -1),\n" +
                "])",listUserBalance.toString())
        val userBalanceTTOrderByDescTTMoneyTTIterator = UserBalanceTTOrderByDescTTMoneyTTIterator()
        listUserBalance.sortingFromModelTTNamedTTNamedTTNamedTTIteratorParameterListModel(userBalanceTTOrderByDescTTMoneyTTIterator)
        assertEquals("ListUserBalance(listModel: [\n" +
                "UserBalance(username: Mitsuya, money: 10),\n" +
                "UserBalance(username: Hook, money: 7),\n" +
                "UserBalance(username: Duramichi, money: 5),\n" +
                "UserBalance(username: Jone, money: 3),\n" +
                "UserBalance(username: Freddy, money: 1),\n" +
                "UserBalance(username: Sexy, money: -1),\n" +
                "])",listUserBalance.toString())
        listUserBalance.updateFromNewModelParameterListModel(UserBalance("Duramichi",15))
        listUserBalance.sortingFromModelTTNamedTTNamedTTNamedTTIteratorParameterListModel(userBalanceTTOrderByDescTTMoneyTTIterator)
        assertEquals("ListUserBalance(listModel: [\n" +
                "UserBalance(username: Duramichi, money: 15),\n" +
                "UserBalance(username: Mitsuya, money: 10),\n" +
                "UserBalance(username: Hook, money: 7),\n" +
                "UserBalance(username: Jone, money: 3),\n" +
                "UserBalance(username: Freddy, money: 1),\n" +
                "UserBalance(username: Sexy, money: -1),\n" +
                "])",listUserBalance.toString())
        listUserBalance.deleteFromUniqueIdByModelParameterListModel("Mitsuya")
        listUserBalance.sortingFromModelTTNamedTTNamedTTNamedTTIteratorParameterListModel(userBalanceTTOrderByDescTTMoneyTTIterator)
        assertEquals("ListUserBalance(listModel: [\n" +
                "UserBalance(username: Duramichi, money: 15),\n" +
                "UserBalance(username: Hook, money: 7),\n" +
                "UserBalance(username: Jone, money: 3),\n" +
                "UserBalance(username: Freddy, money: 1),\n" +
                "UserBalance(username: Sexy, money: -1),\n" +
                "])",listUserBalance.toString())
    }
}