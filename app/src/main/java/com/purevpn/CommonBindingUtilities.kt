package com.purevpn

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableInt
import androidx.viewpager.widget.ViewPager
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.tabs.TabLayout
import com.purevpn.dashboard.DashboardViewPagerAdapter


object CommonBindingUtilities {
    @JvmStatic
    @BindingAdapter("bind:pagerAdapter")
    fun bindViewPagerAdapter(viewPager: ViewPager, activity: AppCompatActivity) {
        viewPager.adapter = DashboardViewPagerAdapter(activity, activity.supportFragmentManager)


    }

    @JvmStatic
    @BindingAdapter( "bind:currentIndex")
    fun currentIndex(viewPager: ViewPager, index: ObservableInt) {
        index.get().apply {
            if (viewPager.currentItem != this)
                viewPager.currentItem = this
        }

    }


    @JvmStatic
    @BindingAdapter("bind:pager")
    fun bindTabViewToPager(tabLayout: TabLayout, viewPager: ViewPager) {
        tabLayout.setupWithViewPager(viewPager)
    }




    @JvmStatic
    @BindingAdapter("bind:flag", "bind:context")
    fun bindCountryFlag(imageView: ImageView, isoCode: String, context: Context) {
        val identifier = context.resources.getIdentifier(isoCode.toLowerCase(), "drawable", context.packageName)
        imageView.setBackgroundResource(identifier)


    }


    @JvmStatic
    @BindingAdapter("bind:fileName")
    fun bindAnimation(aimationView: LottieAnimationView,fileName:String){
        aimationView.setAnimation(fileName)
        aimationView.playAnimation()

    }


}