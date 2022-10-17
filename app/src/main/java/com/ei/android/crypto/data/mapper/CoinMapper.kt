package com.ei.android.crypto.data.mapper

import com.ei.android.crypto.data.db.CoinInfoDB
import com.ei.android.crypto.data.network.models.CoinInfoDTO
import com.ei.android.crypto.data.network.models.CoinInfoJsonContainerDTO
import com.ei.android.crypto.data.network.models.CoinNamesListDTO
import com.ei.android.crypto.domain.CoinInfo
import com.google.gson.Gson
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class CoinMapper {

    fun mapDtoToDbModel(dto: CoinInfoDTO): CoinInfoDB {
        return CoinInfoDB(
            dto.fromSymbol,
            dto.toSymbol,
            dto.price,
            dto.lowDay,
            dto.highDay,
            dto.lastMarket,
            dto.lastUpdate,
            BASE_IMAGE_URL +dto.imageUrl
        )
    }

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDTO): List<CoinInfoDTO> {
        val result = mutableListOf<CoinInfoDTO>()
        val jsonObjects = jsonContainer.json ?: return result
        val coinKeySet = jsonObjects.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObjects.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDTO::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesListDTO: CoinNamesListDTO): String {
        return namesListDTO.names?.map {
            it.coinName?.name
        }?.joinToString(",") ?: ""
    }

    fun mapDbModelToEntity(dbModel: CoinInfoDB): CoinInfo {
        return CoinInfo(
            dbModel.fromSymbol,
            dbModel.toSymbol,
            dbModel.price,
            dbModel.lowDay,
            dbModel.highDay,
            dbModel.lastMarket,
            convertTimestampToTime(dbModel.lastUpdate),
            dbModel.imageUrl
        )
    }
    private fun convertTimestampToTime(timestamp:Long?):String{
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp*1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object{
        private const val BASE_IMAGE_URL = "https://www.cryptocompare.com"

    }
}