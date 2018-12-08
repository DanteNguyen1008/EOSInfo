package com.cat.eosinfo.repo.model

data class Transaction(
    val cpu_usage_us: Int,
    val net_usage_words: Int,
    val status: String,
    val trx: Trx
)