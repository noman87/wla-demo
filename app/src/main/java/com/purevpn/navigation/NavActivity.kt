package com.purevpn.navigation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.purevpn.R

class NavActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var viewModel: NavViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        viewModel = ViewModelProviders.of(this).get(NavViewModel::class.java)
        navController = Navigation.findNavController(this, R.id.host_fragment)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_navigation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navigated = item?.let {
            if (it.title == "Second destination") {

                val directions =
                    DestinationOneFragmentDirections.actionDestinationOneToDestinationTwo(5)
                navController.navigate(directions)

            }
            //NavigationUI.onNavDestinationSelected(it, navController)
        }
        return super.onOptionsItemSelected(item)
    }
}
