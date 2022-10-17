package com.ei.android.crypto.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.ei.android.crypto.data.db.AppDatabase
import com.ei.android.crypto.data.mapper.CoinMapper
import com.ei.android.crypto.data.network.ApiFactory
import com.ei.android.crypto.data.network.ApiService
import com.ei.android.crypto.domain.CoinInfo
import com.ei.android.crypto.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    private val application: Application
) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val mapper = CoinMapper()

    private val apiService = ApiFactory.apiService

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getPriceList()) {
            it.map {dbModel->
                mapper.mapDbModelToEntity(dbModel)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)){
                mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true){
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoListDTO = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoListDTO.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(1000)
        }
    }
}