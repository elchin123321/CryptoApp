package com.ei.android.crypto.data.network.models

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinInfoDTO(
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromSymbol: String,
    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String,
    @SerializedName("PRICE")
    @Expose
    val price: String,
    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Long,
    @SerializedName("HIGHDAY")
    @Expose
    val highDay: String,
    @SerializedName("LOWDAY")
    @Expose
    val lowDay: String,
    @SerializedName("LASTMARKET")
    @Expose
    val lastMarket: String,
    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String
)