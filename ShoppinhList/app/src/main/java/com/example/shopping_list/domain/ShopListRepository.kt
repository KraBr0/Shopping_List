package com.example.shopping_list.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem)

    fun editeShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int):ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}