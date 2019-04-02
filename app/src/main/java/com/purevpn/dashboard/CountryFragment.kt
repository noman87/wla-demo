package com.purevpn.dashboard


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.purevpn.R
import com.purevpn.core.models.CountryModel
import com.purevpn.databinding.FragmentCountryBinding
import kotlinx.android.synthetic.main.fragment_country.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CountryFragment : Fragment() {

    lateinit var binding: FragmentCountryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_country, container, false)
        return binding.root

    }

    private lateinit var dashboardViewModel: DashboardViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.run {
            dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java).also {
                binding.viewModel = it
                binding.context = this
                it.getCountries()

                val observer = Observer<List<CountryModel>> {
                    recycleView.adapter?.notifyDataSetChanged()
                }
                it.list.observe(this@CountryFragment, observer)


            }


        }

    }


}
