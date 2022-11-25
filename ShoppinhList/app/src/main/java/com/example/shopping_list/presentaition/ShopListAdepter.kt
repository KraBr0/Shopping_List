package com.example.shopping_list.presentaition

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shopping_list.R
import com.example.shopping_list.domain.ShopItem

class ShopListAdepter : RecyclerView.Adapter<ShopListAdepter.ShopItemViewHolder>() {

    val list = listOf<ShopItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop_disenabled, parent, false)

        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem=list[position]
        viewHolder.tvName.text=shopItem.name
        viewHolder.tvCount.text= shopItem.count.toString()
        viewHolder.view.setOnLongClickListener{
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }
}