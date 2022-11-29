package com.example.shopping_list.presentaition

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.shopping_list.R
import com.example.shopping_list.domain.ShopItem

class ShopItemActivity:AppCompatActivity() {
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        Log.d("ShopItemActivity", mode.toString())
    }

    companion object{
       private const val EXTRA_SCREEN_MODE = "extra_mode"
       private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
       private const val MODE_EDIT = "mode_edite"
       private const val MODE_ADD = "mode_add"

        fun newIntentAddItem(context:Context):Intent{
            val intent=Intent(context,ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditeItem(context: Context,shopItemId:Int):Intent{
            val intent=Intent(context,ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID,shopItemId)
            return intent
        }
    }
}