package com.purevpn.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.purevpn.core.iView.BindableAdapter
import com.purevpn.core.models.CountryModel
import com.purevpn.databinding.ItemCountryBinding


class CountryAdapter(val context: FragmentActivity) : RecyclerView.Adapter<CountryAdapter.CountryHolder>(),
    BindableAdapter<CountryModel> {

    override fun setData(items: List<CountryModel>) {
        countryList = items
        notifyDataSetChanged()
    }

    override fun changedPositions(positions: Set<Int>) {
        positions.forEach(this::notifyItemChanged)
    }

    private var countryList = emptyList<CountryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemCountryBinding.inflate(layoutInflater, parent, false)
        return CountryHolder(itemBinding)
    }

    override fun getItemCount() = countryList.size

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bind(countryList[position], context)
    }

    class CountryHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: CountryModel, context: FragmentActivity) {
            binding.country = country
            binding.context = context
            binding.executePendingBindings()

        }
    }
}