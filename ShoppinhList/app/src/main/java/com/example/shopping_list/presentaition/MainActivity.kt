package com.example.shopping_list.presentaition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.shopping_list.R
import com.example.shopping_list.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var llshopList: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llshopList = findViewById(R.id.ll_shop_list)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(this) {
            showList(it)
        }

    }

    private fun showList(list: List<ShopItem>) {
        llshopList.removeAllViews()
        for (shopItem in list) {
            val layoutId = if (shopItem.enable) {
                R.layout.item_shop_enabled
            } else {
                R.layout.item_shop_disenabled
            }
            val view = LayoutInflater.from(this).inflate(layoutId, llshopList, false)
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount = view.findViewById<TextView>(R.id.tv_count)
            tvName.text=shopItem.name
            tvCount.text= shopItem.count.toString()
            view.setOnLongClickListener{
                viewModel.changeEnableState(shopItem)
                true
            }
            llshopList.addView(view)
        }
    }
}