package com.assignment.onjuno.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.onjuno.HomeActivity
import com.assignment.onjuno.R
import com.assignment.onjuno.data.CryptoPrice
import com.assignment.onjuno.databinding.CurrentPriceCardHolderBinding
import com.assignment.onjuno.utils.LoadSvg

class CurrentPriceAdapter(val activity: HomeActivity, val type: HomeActivity.TYPE)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var currentPriceList: ArrayList<CryptoPrice?>? = ArrayList()

    fun setCurrPriceList(currentPriceList: ArrayList<CryptoPrice?>?) {
        this.currentPriceList = currentPriceList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CurrPriceHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
        R.layout.current_price_card_holder, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        currentPriceList?.get(position)?.let { (holder as CurrPriceHolder).bind(it) }
    }

    override fun getItemCount(): Int {
        return currentPriceList?.size ?: 0
    }

    inner class CurrPriceHolder(private val binding: CurrentPriceCardHolderBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(cryptoPrice: CryptoPrice) {
                binding.coinName.text = cryptoPrice.title
                binding.coinPrice.text = "$"+cryptoPrice.current_price_in_usd
                LoadSvg().fetchSvg(activity, cryptoPrice.logo, binding.coinIcon)
            }
        }
}