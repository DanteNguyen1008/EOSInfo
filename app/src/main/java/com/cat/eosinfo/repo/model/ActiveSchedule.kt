package com.cat.eosinfo.repo.model

data class ActiveSchedule(
    val producers: List<Producer>,
    val version: Int
)