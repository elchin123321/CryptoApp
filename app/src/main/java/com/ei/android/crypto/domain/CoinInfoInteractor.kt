package com.ei.android.crypto.domain

import androidx.lifecycle.LiveData

class CoinInfoInteractor(private val coinRepository:CoinRepository) {

    fun getCoinInfoList() = coinRepository.getCoinInfoList()


    fun getCoinInfo(fromSymbol:String) = coinRepository.getCoinInfo(fromSymbol)

    suspend fun loadData(){
        coinRepository.loadData()
    }

}