package com.cat.eosinfo.repo.model

import android.os.Parcelable
import com.cat.eosinfo.extension.getIntSafe
import com.cat.eosinfo.extension.getJSONObjectSafe
import com.cat.eosinfo.extension.getStringSafe
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Transaction(
    val cpu_usage_us: Int,
    val net_usage_words: Int,
    val status: String,
    val trxStr: String?,
    val trx: Trx?
) : Parcelable {
    companion object {

        @JvmStatic
        fun fromJSON(jsonObject: JSONObject): Transaction {
            var trx: Trx? = null
            var trxStr: String? = null
            if (jsonObject.get("trx") is JSONObject) {
                val trxJObject = jsonObject.getJSONObjectSafe("trx")

                if (trxJObject != null) {
                    trx = Trx.fromJSON(trxJObject)
                }
            } else if (jsonObject.get("trx") is String) {
                trxStr = jsonObject.getStringSafe("trx")
            }

            return Transaction(
                jsonObject.getIntSafe("cpu_usage_us"),
                jsonObject.getIntSafe("net_usage_words"),
                jsonObject.getStringSafe("status"),
                trxStr,
                trx
            )
        }
    }
}