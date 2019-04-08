package com.purevpn

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.tabs.TabLayout
import com.purevpn.core.enums.ConnectionState
import com.purevpn.core.models.CountryModel
import com.purevpn.dashboard.CountryAdapter
import com.purevpn.dashboard.DashboardActivity
import com.purevpn.dashboard.DashboardViewPagerAdapter


object CommonBindingUtilities {
    @JvmStatic
    @BindingAdapter("bind:pagerAdapter")
    fun bindViewPagerAdapter(viewPager: ViewPager, activity: AppCompatActivity) {
        viewPager.adapter = DashboardViewPagerAdapter(activity, activity.supportFragmentManager)


    }

    @JvmStatic
    @BindingAdapter("bind:currentIndex")
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
    @BindingAdapter("bind:query")
    fun bindQuery(searchView: SearchView, adapter: CountryAdapter) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

        })


    }


    @JvmStatic
    @BindingAdapter("bind:flag", "bind:context")
    fun bindCountryFlag(imageView: ImageView, isoCode: String, context: Context) {
        val identifier = context.resources.getIdentifier(isoCode.toLowerCase(), "drawable", context.packageName)
        imageView.setBackgroundResource(identifier)


    }


    @JvmStatic
    @BindingAdapter("bind:fileName")
    fun bindAnimation(animationView: LottieAnimationView, connectionState: ConnectionState) {
        when (connectionState) {
            ConnectionState.CONNECTED -> {
                animationView.setAnimation("anim/disconnected.json")
                animationView.visibility = View.INVISIBLE
            }
            ConnectionState.CONNECTING -> {
                animationView.visibility = View.VISIBLE
                animationView.setAnimation("anim/connecting.json")

            }
            ConnectionState.DISCONNECTED -> {
                animationView.visibility = View.VISIBLE
                animationView.setAnimation("anim/disconnected_dark.json")
            }
        }
        animationView.playAnimation()

    }

    @JvmStatic
    @BindingAdapter("bind:connectButtonColor", "bind:activity")
    fun bindConnectButtonColor(button: Button, connectionState: ConnectionState, activity: DashboardActivity) {
        activity.runOnUiThread {
            when (connectionState) {
                ConnectionState.CONNECTED -> {
                    button.setBackgroundResource(R.drawable.btn_connected_bg)
                    button.setText(R.string.txt_connected)

                }

                ConnectionState.CONNECTING -> {
                    button.setBackgroundResource(R.drawable.btn_connecting_bg)
                    button.setText(R.string.txt_connecting)

                }
                ConnectionState.DISCONNECTED -> {
                    button.setBackgroundResource(R.drawable.btn_disconnected_bg)
                    button.setText(R.string.txt_connect)

                }
            }
        }


    }

    @JvmStatic
    @BindingAdapter("bind:progressVisibility")
    fun bindProgressVisibility(progressBar: ProgressBar, visibility: ObservableBoolean) {
        if (visibility.get())
            progressBar.visibility = View.VISIBLE
        else
            progressBar.visibility = View.GONE
    }


    @JvmStatic
    @BindingAdapter("bind:list_data")
    fun bindData(
        recyclerView: RecyclerView,
        list: ObservableField<List<CountryModel>>
    ) {
        list.get()?.let {
            val adapter = recyclerView.adapter as CountryAdapter
            adapter.setData(it)
        }
    }


}