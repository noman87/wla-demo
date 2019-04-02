package com.purevpn.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.purevpn.core.models.CountryModel
import com.purevpn.databinding.ItemCountryBinding


class CountryAdapter(val dashboardViewModel: DashboardViewModel) :
    RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    lateinit var context: Context

    private var countryList: MutableLiveData<List<CountryModel>> = MutableLiveData()


    fun setData(items: MutableLiveData<List<CountryModel>>) {
        countryList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemCountryBinding.inflate(layoutInflater, parent, false)
        return CountryHolder(itemBinding, dashboardViewModel)
    }

    override fun getItemCount(): Int {
        countryList.value?.apply {
            return size
        }
        return 0
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bind(countryList.value!![position], context)
    }

    class CountryHolder(val binding: ItemCountryBinding, val dashboardViewModel: DashboardViewModel) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: CountryModel, context: Context) {
            binding.country = country
            binding.context = context
            binding.viewModel = dashboardViewModel
            binding.executePendingBindings()

        }
    }
}