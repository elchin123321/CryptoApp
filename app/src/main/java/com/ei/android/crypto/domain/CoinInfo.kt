package com.ei.android.crypto.domain

data class CoinInfo(
    val fromSymbol: String,
    val toSymbol: String,
    val price: String,
    val lowDay: String,
    val highDay: String,
    val lastMarket: String,
    val lastUpdate: String,
    val imageUrl: String
)