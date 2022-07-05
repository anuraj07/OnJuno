package com.assignment.onjuno.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.onjuno.HomeActivity
import com.assignment.onjuno.R
import com.assignment.onjuno.data.YourCryptoHolding
import com.assignment.onjuno.databinding.YourCryptoHoldingCardholderBinding

class CryptoHoldingAdapter(val activity: HomeActivity, val type: HomeActivity.TYPE)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var holdingList: ArrayList<YourCryptoHolding?>? = ArrayList()

    fun setHoldingList(yourCryptoHolding: ArrayList<YourCryptoHolding?>?) {
        this.holdingList = yourCryptoHolding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CryptoHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.your_crypto_holding_cardholder, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holdingList?.get(position)?.let { (holder as CryptoHolder).bind(it) }
    }

    override fun getItemCount(): Int {
        return holdingList?.size ?: 0
    }

    inner class CryptoHolder(private val binding: YourCryptoHoldingCardholderBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(yourCryptoHolding: YourCryptoHolding) {
                binding.balInToken.text = yourCryptoHolding.current_bal_in_token+" "+yourCryptoHolding.title
                binding.coinName.text = yourCryptoHolding.title
                binding.balInUsd.text = "$"+yourCryptoHolding.current_bal_in_usd
                if (type == HomeActivity.TYPE.EMPTY_STATE) {
                    binding.balInUsd.visibility = View.GONE
                    binding.balInToken.visibility = View.GONE
                    binding.deposit.visibility = View.VISIBLE
                    binding.buy.visibility = View.VISIBLE
                }
            }

    }
}