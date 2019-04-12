package com.purevpn.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.purevpn.R
import com.purevpn.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    lateinit var dashboardViewModel: DashboardViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDashboardBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)




        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java).apply {
            binding.viewmodel = this
            binding.activityContext = this@DashboardActivity
            init()

        }


    }

    override fun onDestroy() {
        super.onDestroy()
    }


}
