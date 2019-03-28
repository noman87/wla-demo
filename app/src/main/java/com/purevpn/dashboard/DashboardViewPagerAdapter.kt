package com.purevpn.dashboard

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.purevpn.R

class DashboardViewPagerAdapter(private val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    var fragments: List<Fragment> = listOf(DashboardFragment(), CountryFragment())
    var fragmentsName: List<String> = listOf(context.getString(R.string.dasboard), context.getString(R.string.country))


    override fun getCount(): Int {
        return 2

    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentsName[position]
    }
}