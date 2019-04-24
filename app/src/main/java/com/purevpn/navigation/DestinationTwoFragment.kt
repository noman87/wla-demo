package com.purevpn.navigation


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.purevpn.R



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DestinationTwoFragment : Fragment() {


    val args: DestinationTwoFragmentArgs by navArgs()
    private lateinit var viewModel: NavViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.e("ID is", "" + args.id)


        viewModel = activity?.run {
            ViewModelProviders.of(this).get(NavViewModel::class.java)
        }!!


        viewModel.items.observe(this, Observer<String> {
            Log.e("Item", "Changed")
        })
        return inflater.inflate(R.layout.fragment_destination_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}
