package com.example.shopping_list.domain

data class ShopItem(
    val name: String,
    val caount: Int,
    val enable: Boolean,
    var id: Int= UNDEFINED_ID
)
{
    companion object{
        const val UNDEFINED_ID=-1
    }
}
