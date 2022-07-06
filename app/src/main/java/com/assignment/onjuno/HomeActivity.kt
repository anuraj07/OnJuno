package com.assignment.onjuno

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.assignment.onjuno.adapter.CryptoHoldingAdapter
import com.assignment.onjuno.adapter.CurrentPriceAdapter
import com.assignment.onjuno.adapter.RecentTransactionAdapter
import com.assignment.onjuno.base.ViewBindingBaseActivity
import com.assignment.onjuno.data.*
import com.assignment.onjuno.databinding.ActivityHomeBinding
import com.assignment.onjuno.listener.YourHoldingsCallbacks
import com.assignment.onjuno.viewmodels.HomeViewModel
import com.assignment.onjuno.viewmodels.HomeViewModelFactory
import javax.inject.Inject

class HomeActivity : ViewBindingBaseActivity<ActivityHomeBinding>(), YourHoldingsCallbacks {

    override val layoutId: Int
        get() = R.layout.activity_home

    enum class TYPE { VALUE_STATE, EMPTY_STATE}

    private var type: TYPE? = null
    lateinit var homeViewModel: HomeViewModel
    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    private lateinit var cryptoHoldingAdapter: CryptoHoldingAdapter
    private lateinit var recentTransactionAdapter: RecentTransactionAdapter
    private lateinit var currPriceAdapter: CurrentPriceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as OnJunoApplication).applicationComponent.inject(this)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)

        type = intent.getSerializableExtra(TYPE) as TYPE?

        binding.progressBar.visibility = View.VISIBLE
        allViewsVisiblity(View.GONE)

        if (type == HomeActivity.TYPE.EMPTY_STATE)
            observeEmptyState()
        else if (type == HomeActivity.TYPE.VALUE_STATE)
            observeValueState()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.viewAll.setOnClickListener {
            showToast("Not Implemented")
        }
    }

    private fun observeEmptyState() {
        homeViewModel.emptyStateLiveData.observe(this) {
            setUpEmptyState(it)
        }
    }

    private fun observeValueState() {
        homeViewModel.valueStateLiveData.observe(this) {
            setUpValueState(it)
        }
    }

    private fun setUpEmptyState(emptyState: emptyState) {
        binding.cryptoAccTitle.text = emptyState.crypto_balance?.title
        binding.cryptoAccSubtitle.text = emptyState.crypto_balance?.subtitle
        binding.cryptoCurrBal.visibility = View.GONE
        binding.deposit.visibility = View.VISIBLE
        setUpYourHoldingRecyclerView(HomeActivity.TYPE.EMPTY_STATE, emptyState.your_crypto_holdings)
        setUpRecentTxnRecyclerView(HomeActivity.TYPE.EMPTY_STATE, emptyState.all_transactions)
        setUpCurrPriceRecyclerView(HomeActivity.TYPE.EMPTY_STATE, emptyState.crypto_prices)
        binding.progressBar.visibility = View.GONE
        allViewsVisiblity(View.VISIBLE)
    }

    private fun setUpValueState(valueState: valueState) {
        binding.cryptoAccTitle.text = valueState.crypto_balance?.title
        binding.cryptoAccSubtitle.text = valueState.crypto_balance?.subtitle
        binding.cryptoCurrBal.text = "$"+valueState.crypto_balance?.current_bal_in_usd
        setUpYourHoldingRecyclerView(HomeActivity.TYPE.VALUE_STATE, valueState.your_crypto_holdings)
        setUpRecentTxnRecyclerView(HomeActivity.TYPE.VALUE_STATE, valueState.all_transactions)
        setUpCurrPriceRecyclerView(HomeActivity.TYPE.VALUE_STATE, valueState.crypto_prices)
        binding.progressBar.visibility = View.GONE
        allViewsVisiblity(View.VISIBLE)
    }

    private fun setUpYourHoldingRecyclerView(type: TYPE, yourCryptoHoldings: ArrayList<YourCryptoHolding?>?) {
        cryptoHoldingAdapter = CryptoHoldingAdapter(this, type)
        cryptoHoldingAdapter.setListener(this)
        binding.yourHoldingRecyclerview.adapter = cryptoHoldingAdapter
        cryptoHoldingAdapter.setHoldingList(yourCryptoHoldings)
        binding.yourHoldingRecyclerview.adapter?.notifyDataSetChanged()
    }

    private fun setUpRecentTxnRecyclerView(type: TYPE, allTransactions: ArrayList<AllTransaction?>?) {
        recentTransactionAdapter = RecentTransactionAdapter(this, type)
        binding.recentTransRecyclerview.adapter = recentTransactionAdapter
        recentTransactionAdapter.setTransactionList(allTransactions)
        binding.recentTransRecyclerview.adapter?.notifyDataSetChanged()
    }

    private fun setUpCurrPriceRecyclerView(type: TYPE, cryptoPrices: ArrayList<CryptoPrice?>?) {
        currPriceAdapter = CurrentPriceAdapter(this, type)
        binding.currPriceRecyclerview.adapter = currPriceAdapter
        currPriceAdapter.setCurrPriceList(cryptoPrices)
        binding.currPriceRecyclerview.adapter?.notifyDataSetChanged()
    }

    private fun allViewsVisiblity(visibility: Int) {
        binding.cryptoWallet.visibility = visibility
        binding.view1.visibility = visibility
        binding.yourHolding.visibility = visibility
        binding.yourHoldingRecyclerview.visibility = visibility
        binding.view2.visibility = visibility
        binding.recentTxn.visibility = visibility
        binding.recentTransRecyclerview.visibility = visibility
        binding.view3.visibility = visibility
        binding.currPrice.visibility = visibility
        binding.currPriceRecyclerview.visibility = visibility
    }

    companion object {
        private const val TYPE = "type"
        fun start (context: Context, type: TYPE) {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(TYPE, type)
            context.startActivity(intent)
        }
    }

    override fun onBuyClicked(yourCryptoHolding: YourCryptoHolding, position: Int) {
        val txn = AllTransaction(yourCryptoHolding.title+" Bought",
            "24.05", yourCryptoHolding.logo, "", "few second ago")
        val list = recentTransactionAdapter.getAllTxnList()
        list?.add(txn)
        recentTransactionAdapter.setTransactionList(list)
        recentTransactionAdapter.notifyDataSetChanged()
    }
}