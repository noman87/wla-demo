package com.purevpn.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.purevpn.R
import com.purevpn.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDashboardBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        val viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        binding.viewmodel = viewModel
        binding.activityContext = this

    }


}
