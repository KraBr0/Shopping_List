package com.example.shopping_list.presentaition

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping_list.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdepter: ShopListAdepter
    private var shopItemConteiner: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shopItemConteiner = findViewById(R.id.shop_item_container)
        setRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(this) {
            shopListAdepter.submitList(it)
        }
        val buttonAddItem = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
        buttonAddItem.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceAddItem())
            }
        }

    }

    private fun isOnePaneMode(): Boolean {
        return shopItemConteiner == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .add(R.id.shop_item_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        with(rvShopList) {
            shopListAdepter = ShopListAdepter()
            adapter = shopListAdepter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdepter.VIEW_TYPE_ENABLED,
                ShopListAdepter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdepter.VIEW_TYPE_DISABLED,
                ShopListAdepter.MAX_POOL_SIZE
            )
        }
        setUpClickLongLister()
        setUpClickListener()
        setUpSwipeLister(rvShopList)
    }

    private fun setUpSwipeLister(rvShopList: RecyclerView) {
        val callback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdepter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    private fun setUpClickLongLister() {
        shopListAdepter.onShopItemOnLongClickLister = {
            viewModel.changeEnableState(it)
        }

    }

    private fun setUpClickListener() {
        shopListAdepter.onShopItemOnClickLister = {
            if (isOnePaneMode()){
                val intent = ShopItemActivity.newIntentEditeItem(this, it.id)
                startActivity(intent)
            }
            else{
                launchFragment(ShopItemFragment.newInstanceEditeItem(it.id))
            }
        }
    }
}

