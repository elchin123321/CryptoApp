package com.ei.android.crypto.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNamesListDTO (
    @SerializedName("Data")
    @Expose
    val names:List<CoinNameContainerDTO>? = null
)