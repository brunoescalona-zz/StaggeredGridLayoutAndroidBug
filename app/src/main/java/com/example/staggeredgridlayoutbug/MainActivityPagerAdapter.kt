package com.example.staggeredgridlayoutbug

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainActivityPagerAdapter (
    private val fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private var pages = listOfNotNull(
        MainPage.FIRST,
        MainPage.SECOND,
        MainPage.THIRD
    )

    fun getPagePosition(page: MainPage) = pages.indexOf(page)

    override fun getItemCount() = pages.size

    override fun createFragment(position: Int): Fragment {
        return when (pages[position]) {
            MainPage.FIRST -> FirstFragment()
            MainPage.SECOND -> SecondFragment()
            MainPage.THIRD -> ThirdFragment()
        }
    }

    override fun getItemId(position: Int): Long {
        return pages.getOrNull(position)?.ordinal?.toLong() ?: RecyclerView.NO_ID
    }

    override fun containsItem(itemId: Long): Boolean {
        return pages.any { it.ordinal.toLong() == itemId }
    }

    // To get the current fragment we use the internal fragment tag
    // More info https://stackoverflow.com/a/61178226/1635488
    fun getFragment(position: Int) = fragmentActivity.supportFragmentManager.findFragmentByTag("f${getItemId(position)}")

    enum class MainPage {
        FIRST,
        SECOND,
        THIRD
    }
}
