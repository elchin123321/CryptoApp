package com.ei.android.crypto.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfo(
    @SerializedName("Name")
    @Expose
    val name: String? = null,
)
