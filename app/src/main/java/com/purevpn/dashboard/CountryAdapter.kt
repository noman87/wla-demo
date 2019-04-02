package com.purevpn.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.purevpn.core.iView.BindableAdapter
import com.purevpn.core.models.CountryModel
import com.purevpn.databinding.ItemCountryBinding


class CountryAdapter(val context: FragmentActivity) : RecyclerView.Adapter<CountryAdapter.CountryHolder>(),
    BindableAdapter<CountryModel> {

    override fun setData(items: MutableLiveData<List<CountryModel>>) {
        countryList = items
        notifyDataSetChanged()
    }

    override fun changedPositions(positions: Set<Int>) {
        positions.forEach(this::notifyItemChanged)
    }

    var countryList: MutableLiveData<List<CountryModel>> = MutableLiveData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemCountryBinding.inflate(layoutInflater, parent, false)
        return CountryHolder(itemBinding)
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

    class CountryHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: CountryModel, context: FragmentActivity) {
            binding.country = country
            binding.context = context
            binding.executePendingBindings()

        }
    }
}