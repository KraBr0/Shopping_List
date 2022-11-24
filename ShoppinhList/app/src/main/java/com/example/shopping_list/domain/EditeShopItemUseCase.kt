package com.example.shopping_list.domain

class EditeShopItemUseCase (private  val shopListRepository: ShopListRepository) {
    fun editeShopItem(shopItem: ShopItem){
shopListRepository.editeShopItem(shopItem)
    }
}