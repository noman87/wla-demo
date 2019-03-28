package com.purevpn

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.purevpn.dashboard.DashboardViewPagerAdapter


object CommonBindingUtilities {
    @JvmStatic
    @BindingAdapter("android:pagerAdapter")
    fun bindViewPagerAdapter(viewPager: ViewPager,activity : AppCompatActivity) {
        viewPager.adapter = DashboardViewPagerAdapter(activity, activity.supportFragmentManager)
    }


}