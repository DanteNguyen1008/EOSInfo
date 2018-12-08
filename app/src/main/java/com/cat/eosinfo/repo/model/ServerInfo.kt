package com.cat.eosinfo.repo.model

import com.cat.eosinfo.extension.getIntSafe
import com.cat.eosinfo.extension.getStringSafe
import org.json.JSONObject

data class ServerInfo(
    val chain_id: String,
    val head_block_id: String,
    val head_block_num: Int,
    val head_block_producer: String,
    val head_block_time: String
) {
    companion object {

        /**
         * Convert server info from JSON
         *
         * @param str String
         */
        @JvmStatic
        fun fromJSON(str: String): ServerInfo {
            val root = JSONObject(str)
            return ServerInfo(
                root.getStringSafe("chain_id"),
                root.getStringSafe("head_block_id"),
                root.getIntSafe("head_block_num"),
                root.getStringSafe("head_block_producer"),
                root.getStringSafe("head_block_time")
            )
        }
    }
}