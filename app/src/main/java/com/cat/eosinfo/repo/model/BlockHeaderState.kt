package com.cat.eosinfo.repo.model

data class BlockHeaderState(
    val active_schedule: ActiveSchedule,
    val bft_irreversible_blocknum: Int,
    val block_num: Int,
    val block_signing_key: String,
    val blockroot_merkle: BlockrootMerkle,
    val confirm_count: List<Int>,
    val confirmations: List<Any>,
    val dpos_irreversible_blocknum: Int,
    val dpos_proposed_irreversible_blocknum: Int,
    val header: Header,
    val id: String,
    val pending_schedule: PendingSchedule,
    val pending_schedule_hash: String,
    val pending_schedule_lib_num: Int,
    val producer_to_last_implied_irb: List<Any>,
    val producer_to_last_produced: List<Any>
)