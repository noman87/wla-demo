package com.purevpn.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.purevpn.R
import com.purevpn.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDashboardBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)

        val index = Observer<Int> {

            binding.pager.currentItem = it
        }

        val viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java).apply {
            binding.viewmodel = this
            binding.activityContext = this@DashboardActivity
            binding.pager.currentItem = 0

            this.currentPage.observe(this@DashboardActivity,index)


        }






    }


}
