package com.cat.eosinfo.repo.model

data class ServerInfo(
    val block_cpu_limit: Int,
    val block_net_limit: Int,
    val chain_id: String,
    val head_block_id: String,
    val head_block_num: Int,
    val head_block_producer: String,
    val head_block_time: String,
    val last_irreversible_block_id: String,
    val last_irreversible_block_num: Int,
    val server_version: String,
    val server_version_string: String,
    val virtual_block_cpu_limit: Int,
    val virtual_block_net_limit: Int
)