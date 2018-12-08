package com.cat.eosinfo.repo.model

import android.os.Parcelable
import com.cat.eosinfo.extension.getJSONArraySafe
import com.cat.eosinfo.extension.getJSONObjectSafe
import com.cat.eosinfo.extension.getStringSafe
import com.cat.eosinfo.extension.toList
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Trx(
    val id: String,
    val signatures: List<String>,
    val transaction: TransactionX?
) : Parcelable {
    companion object {

        @JvmStatic
        fun fromJSON(jsonObject: JSONObject): Trx {
            val signaturesArr = jsonObject.getJSONArraySafe("signatures")

            val signatures = ArrayList<String>()
            if (signaturesArr != null) {
                signatures.addAll(signaturesArr.toList().map { it as String })
            }

            var transaction : TransactionX? = null
            val transactionJObject = jsonObject.getJSONObjectSafe("transaction")
            if (transactionJObject != null) {
                transaction = TransactionX.fromJSON(transactionJObject)
            }

            return Trx(
                jsonObject.getStringSafe("id"),
                signatures,
                transaction
            )
        }
    }
}