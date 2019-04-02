package com.purevpn

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.purevpn.dashboard.DashboardViewPagerAdapter


object CommonBindingUtilities {
    @JvmStatic
    @BindingAdapter("bind:pagerAdapter", "bind:currentIndex")
    fun bindViewPagerAdapter(viewPager: ViewPager, activity: AppCompatActivity, index: Int) {
        viewPager.adapter = DashboardViewPagerAdapter(activity, activity.supportFragmentManager)
        viewPager.currentItem = index
    }

    @JvmStatic
    @BindingAdapter("bind:pager")
    fun bindTabViewToPager(tabLayout: TabLayout, viewPager: ViewPager) {
        tabLayout.setupWithViewPager(viewPager)
    }

    @JvmStatic
    @BindingAdapter("bind:currentIndex")
    fun bindCurrentIndexOfViewPager(viewPager: ViewPager, index: Int) {
        viewPager.currentItem = index
    }


    @JvmStatic
    @BindingAdapter("bind:flag", "bind:context")
    fun bindCountryFlag(imageView: ImageView, isoCode: String, context: Context) {
        val identifier = context.resources.getIdentifier(isoCode.toLowerCase(), "drawable", context.packageName)
        imageView.setBackgroundResource(identifier)


    }


}