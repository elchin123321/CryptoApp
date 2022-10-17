package com.ei.android.crypto.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_price_list")
data class CoinInfoDB(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String,
    val price: String,
    val lowDay: String,
    val highDay: String,
    val lastMarket: String,
    val lastUpdate: Long,
    val imageUrl: String
)