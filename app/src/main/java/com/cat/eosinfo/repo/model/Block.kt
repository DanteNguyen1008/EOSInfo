package com.cat.eosinfo.repo.model

data class Block(
    val action_mroot: String,
    val block_extensions: List<Any>,
    val block_num: Int,
    val confirmed: Int,
    val header_extensions: List<Any>,
    val id: String,
    val new_producers: Any,
    val previous: String,
    val producer: String,
    val producer_signature: String,
    val ref_block_prefix: Long,
    val schedule_version: Int,
    val timestamp: String,
    val transaction_mroot: String,
    val transactions: List<Transaction>
)