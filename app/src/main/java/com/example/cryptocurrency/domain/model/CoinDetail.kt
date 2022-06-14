package com.example.cryptocurrency.domain.model

import com.example.cryptocurrency.data.remote.dto.TeamMember

data class CoinDetail (
    var coinId:String,
    var name:String,
    var description:String,
    var symbol:String,
    var rank:Int,
    var active:Boolean,
    var tags:List<String>,
    var team:List<TeamMember>
)