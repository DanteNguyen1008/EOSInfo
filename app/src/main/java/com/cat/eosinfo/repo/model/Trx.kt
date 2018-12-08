package com.cat.eosinfo.repo.model

data class Trx(
    val compression: String,
    val context_free_data: List<Any>,
    val id: String,
    val packed_context_free_data: String,
    val packed_trx: String,
    val signatures: List<String>,
    val transaction: TransactionX
)