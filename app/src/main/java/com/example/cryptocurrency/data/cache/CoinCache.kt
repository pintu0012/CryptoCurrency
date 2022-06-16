package com.example.cryptocurrency.data.cache

import com.example.cryptocurrency.data.remote.dto.CoinDto
import com.example.cryptocurrency.domain.model.Coin

interface CoinCache {
    suspend fun getCoinList(): List<CoinDto>
    suspend fun saveCoinList(coinList:List<CoinDto>)
    suspend fun isCached(): Boolean
    suspend fun setLastCacheTime(lastCache: Long)
    suspend fun isExpired(): Boolean
}