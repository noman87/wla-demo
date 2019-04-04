package com.purevpn.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.purevpn.core.models.CountryModel
import com.purevpn.databinding.ItemCountryBinding


class CountryAdapter(private val dashboardViewModel: DashboardViewModel) :
    RecyclerView.Adapter<CountryAdapter.CountryHolder>(), Filterable {
    private var searchList: List<CountryModel>? = null

    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    searchList = countryList.value
                } else {
                    val filteredList = ArrayList<CountryModel>()
                    for (row in countryList.value!!) {
                        if (row.name!!.toLowerCase().contains(charString.toLowerCase()) ||
                            row.name!!.contains(charSequence)
                        ) {
                            filteredList.add(row)
                        }
                    }

                    searchList = filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = searchList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                searchList = filterResults.values as ArrayList<CountryModel>
                notifyDataSetChanged()
            }
        }


    }

    lateinit var context: Context

    private var countryList: MutableLiveData<List<CountryModel>> = MutableLiveData()


    fun setData(items: MutableLiveData<List<CountryModel>>) {
        countryList = items
        searchList = items.value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemCountryBinding.inflate(layoutInflater, parent, false)
        return CountryHolder(itemBinding, dashboardViewModel)
    }

    override fun getItemCount(): Int {
        searchList?.apply {
            return size
        }
        return 0
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bind(searchList!![position], context)
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