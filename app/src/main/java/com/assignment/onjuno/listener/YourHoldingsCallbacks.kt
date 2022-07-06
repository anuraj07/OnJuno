package com.assignment.onjuno.listener

import com.assignment.onjuno.data.YourCryptoHolding

interface YourHoldingsCallbacks {
    fun onBuyClicked(yourCryptoHolding: YourCryptoHolding, position: Int)
}