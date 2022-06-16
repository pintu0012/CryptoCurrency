package com.example.caches.models

data class CoinCacheEntity (
        
        val id: String,
        val is_active: Boolean,
        val is_new: Boolean,
        val name: String,
        val rank: Int,
        val symbol: String,
        val type: String
        )