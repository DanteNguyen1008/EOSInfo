package com.cat.eosinfo.repo.model

data class Header(
    val action_mroot: String,
    val confirmed: Int,
    val header_extensions: List<Any>,
    val previous: String,
    val producer: String,
    val producer_signature: String,
    val schedule_version: Int,
    val timestamp: String,
    val transaction_mroot: String
)