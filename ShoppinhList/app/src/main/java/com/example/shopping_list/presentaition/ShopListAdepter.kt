package com.example.shopping_list.presentaition

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shopping_list.R
import com.example.shopping_list.domain.ShopItem

class ShopListAdepter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemOnLongClickLister: ((ShopItem) -> Unit)? = null
    var onShopItemOnClickLister: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disenabled
            else -> throw RuntimeException("Unknown view Type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)

        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        viewHolder.view.setOnLongClickListener {
            onShopItemOnLongClickLister?.invoke(shopItem)
            true
        }
        viewHolder.view.setOnClickListener {
            onShopItemOnClickLister?.invoke(shopItem)
        }
        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()

    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enable) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }


    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 30
    }
}