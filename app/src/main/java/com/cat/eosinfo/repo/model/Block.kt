package com.cat.eosinfo.repo.model

import android.os.Parcelable
import com.cat.eosinfo.extension.getIntSafe
import com.cat.eosinfo.extension.getJSONArraySafe
import com.cat.eosinfo.extension.getStringSafe
import com.cat.eosinfo.extension.toList
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Block(
    var id: String?,
    var block_num: Int? = null,
    var previous: String? = null,
    var producer: String? = null,
    var producer_signature: String? = null,
    var transactions: List<Transaction>? = null
) : Parcelable {
    companion object {
        @JvmStatic
        fun empty20Items(): ArrayList<Block> {
            val result = ArrayList<Block>()
            for (i in 1..20) {
                result.add(Block(null))
            }

            return result
        }

        @JvmStatic
        fun fromJSON(json: String): Block {
            val root = JSONObject(json)
            val block = Block(
                root.getString("id")
            )

            block.block_num = root.getIntSafe("block_num")
            block.previous = root.getStringSafe("previous")
            block.producer = root.getStringSafe("producer")
            block.producer_signature = root.getStringSafe("producer_signature")

            val transactionArr = root.getJSONArraySafe("transactions")
            if (transactionArr != null) {
                block.transactions = transactionArr.toList().map { Transaction.fromJSON(it as JSONObject) }
            }

            return block
        }
    }
}