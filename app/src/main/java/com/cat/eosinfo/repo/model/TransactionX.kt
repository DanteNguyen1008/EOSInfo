package com.cat.eosinfo.repo.model

data class TransactionX(
    val actions: List<Action>,
    val context_free_actions: List<Any>,
    val delay_sec: Int,
    val expiration: String,
    val max_cpu_usage_ms: Int,
    val max_net_usage_words: Int,
    val ref_block_num: Int,
    val ref_block_prefix: Int,
    val transaction_extensions: List<Any>
)