package com.example.shopping_list.domain

class GetShopListUseCase(private val shopListRepository: ShopListRepository){

    fun getShopList(): List<ShopItem> {
        return shopListRepository.getShopList()
    }
}