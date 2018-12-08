package com.cat.eosinfo.repo.model

data class PendingSchedule(
    val producers: List<Any>,
    val version: Int
)