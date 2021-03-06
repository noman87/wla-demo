package com.purevpn.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.purevpn.R


class DestinationOneFragment : Fragment() {

    private lateinit var viewModel: NavViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(NavViewModel::class.java)
        }!!


        /*viewModel.items.observe(this, Observer<String> {
            Log.e("Item", "Changed")
        })*/

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_destination_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //var actionNext = DestinationOneFragmentDirections.actionNext(TestModel(1, "noman"))


    }
}
