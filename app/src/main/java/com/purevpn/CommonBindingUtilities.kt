package com.purevpn

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.purevpn.core.iView.BindableAdapter
import com.purevpn.dashboard.DashboardViewPagerAdapter


object CommonBindingUtilities {
    @JvmStatic
    @BindingAdapter("android:pagerAdapter")
    fun bindViewPagerAdapter(viewPager: ViewPager,activity : AppCompatActivity) {
        viewPager.adapter = DashboardViewPagerAdapter(activity, activity.supportFragmentManager)
    }

    @JvmStatic
    @BindingAdapter("bind:pager")
    fun bindTabViewToPager(tabLayout: TabLayout,viewPager: ViewPager){
        tabLayout.setupWithViewPager(viewPager)
    }


    @JvmStatic
    @BindingAdapter("bind:flag","bind:context")
    fun bindCountryFlag(imageView: ImageView,isoCode:String,context: Context){
        val identifier = context.resources.getIdentifier(isoCode, "drawable", context.packageName)
        imageView.setBackgroundResource(identifier)



    }

    @JvmStatic
    @BindingAdapter("bind:data")
    fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: List<T>) {
        if (recyclerView.adapter is BindableAdapter<*>) {
            (recyclerView.adapter as BindableAdapter<T>).setData(items)
        }
    }


}