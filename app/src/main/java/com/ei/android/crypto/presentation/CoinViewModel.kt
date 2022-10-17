package com.ei.android.crypto.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ei.android.crypto.data.network.ApiFactory
import com.ei.android.crypto.data.db.AppDatabase
import com.ei.android.crypto.data.db.CoinInfoDB
import com.ei.android.crypto.data.network.models.CoinInfoJsonContainerDTO
import com.ei.android.crypto.data.repository.CoinRepositoryImpl
import com.ei.android.crypto.domain.CoinInfo
import com.ei.android.crypto.domain.CoinInfoInteractor
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)
    private val interactor = CoinInfoInteractor(repository)
    val coinInfoList = interactor.getCoinInfoList()


    fun getDetailInfo(fSym:String) = interactor.getCoinInfo(fSym)


    init {
        viewModelScope.launch {
            interactor.loadData()
        }
    }



}