package com.assignment.onjuno.data

data class valueState(
    val all_transactions: ArrayList<AllTransaction?>?,
    val crypto_balance: CryptoBalance?,
    val crypto_prices: ArrayList<CryptoPrice?>?,
    val your_crypto_holdings: ArrayList<YourCryptoHolding?>?
)

data class AllTransaction(
    val title: String?,
    val txn_amount: String?,
    val txn_logo: String?,
    val txn_sub_amount: String?,
    val txn_time: String?
)

data class CryptoBalance(
    val current_bal_in_usd: String?,
    val subtitle: String?,
    val title: String?
)

data class CryptoPrice(
    val current_price_in_usd: String?,
    val logo: String?,
    val title: String?
)

data class YourCryptoHolding(
    val current_bal_in_token: String?,
    val current_bal_in_usd: String?,
    val logo: String?,
    val title: String?
)