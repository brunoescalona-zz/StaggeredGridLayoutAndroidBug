package com.example.staggeredgridlayoutbug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.staggeredgridlayoutbug.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var ui: ActivityMainBinding
    private val pagerAdapter by lazy { MainActivityPagerAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        ui.viewPager.adapter = pagerAdapter
        ui.viewPager.isUserInputEnabled = false

        ui.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            val newPagePosition = getPagePosition(menuItem.itemId)
            ui.viewPager.setCurrentItem(newPagePosition, false)
            true
        }
    }

    private fun getPagePosition(bottomNavigationItemId: Int): Int {
        val page = when (bottomNavigationItemId) {
            R.id.first -> MainActivityPagerAdapter.MainPage.FIRST
            R.id.second -> MainActivityPagerAdapter.MainPage.SECOND
            R.id.third -> MainActivityPagerAdapter.MainPage.THIRD
            else -> throw AssertionError("Unknown menu item selected with $bottomNavigationItemId")
        }
        return pagerAdapter.getPagePosition(page)
    }
}