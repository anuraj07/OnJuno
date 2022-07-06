package com.assignment.onjuno.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.onjuno.HomeActivity
import com.assignment.onjuno.R
import com.assignment.onjuno.data.AllTransaction
import com.assignment.onjuno.databinding.RecentTtransactionCardHolderBinding
import com.assignment.onjuno.utils.LoadSvg

class RecentTransactionAdapter(val activity: HomeActivity, val type: HomeActivity.TYPE)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var allTransactionList: ArrayList<AllTransaction?>? = ArrayList()

    fun setTransactionList(transactionList: ArrayList<AllTransaction?>?) {
        this.allTransactionList = transactionList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TxnHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
        R.layout.recent_ttransaction_card_holder, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        allTransactionList?.get(position)?.let { (holder as TxnHolder).bind(it) }
    }

    override fun getItemCount(): Int {
        return allTransactionList?.size ?: 0
    }

    inner class TxnHolder(private val binding: RecentTtransactionCardHolderBinding)
        :RecyclerView.ViewHolder(binding.root) {
            fun bind (transaction: AllTransaction) {
                binding.txnName.text = transaction.title
                binding.txnTime.text = transaction.txn_time
                binding.txnAmt.text = "$"+transaction.txn_amount
                LoadSvg().fetchSvg(activity, transaction.txn_logo, binding.txnIcon)
            }
        }
}