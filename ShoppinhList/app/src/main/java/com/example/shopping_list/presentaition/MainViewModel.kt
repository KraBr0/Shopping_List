package com.example.shopping_list.presentaition

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopping_list.data.ShopListRepositoryImpl
import com.example.shopping_list.domain.DeleteShopItemUseCase
import com.example.shopping_list.domain.EditeShopItemUseCase
import com.example.shopping_list.domain.GetShopListUseCase
import com.example.shopping_list.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository=ShopListRepositoryImpl

    private val getShopListUseCase =GetShopListUseCase(repository)
    private val deleteShopItemUseCase =DeleteShopItemUseCase(repository)
    private val editeShopItemUseCase =EditeShopItemUseCase(repository)

    val shopList= getShopListUseCase.getShopList()
    

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)

    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enable = !shopItem.enable)
        editeShopItemUseCase.editeShopItem(newItem)
    }
}