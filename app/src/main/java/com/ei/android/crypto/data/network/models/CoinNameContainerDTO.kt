package com.ei.android.crypto.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNameContainerDTO(
    @SerializedName("CoinInfo")
    @Expose
    val coinName: CoinNameDTO? = null
)