package com.assignment.onjuno.data

data class emptyState(
    val all_transactions: List<AllTransaction?>?,
    val crypto_balance: CryptoBalance?,
    val crypto_prices: List<CryptoPrice?>?,
    val your_crypto_holdings: List<YourCryptoHolding?>?
)