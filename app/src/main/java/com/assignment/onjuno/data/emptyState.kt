package com.assignment.onjuno.data

data class emptyState(
    val all_transactions: ArrayList<AllTransaction?>?,
    val crypto_balance: CryptoBalance?,
    val crypto_prices: ArrayList<CryptoPrice?>?,
    val your_crypto_holdings: ArrayList<YourCryptoHolding?>?
)