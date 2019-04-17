package com.purevpn.dashboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class DashboardViewPagerAdapter(val viewModel: DashboardViewModel, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {


    override fun getCount(): Int {
        return 2

    }

    override fun getItem(position: Int): Fragment {
        return viewModel.fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return viewModel.fragmentsName[position]
    }
}