package com.purevpn.dashboard


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.purevpn.R
import com.purevpn.databinding.FragmentSmartConnectBinding
import org.koin.standalone.KoinComponent

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DashboardFragment : Fragment(), KoinComponent {

    lateinit var binding: FragmentSmartConnectBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_smart_connect, container, false);
        binding.animationView.playAnimation()
        return binding.root
    }

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.run {
            dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)

        }
    }


}
