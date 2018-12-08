package com.cat.eosinfo.repo.model

import android.os.Parcelable
import com.cat.eosinfo.extension.getJSONArraySafe
import com.cat.eosinfo.extension.toList
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class TransactionX(val actions: List<Action>) : Parcelable {
    companion object {

        @JvmStatic
        fun fromJSON(jsonObject: JSONObject): TransactionX {
            val actions = ArrayList<Action>()
            val actionsArr = jsonObject.getJSONArraySafe("actions")

            if (actionsArr != null) {
                actions.addAll(actionsArr.toList().map { Action.fromJSON(it as JSONObject) })
            }

            return TransactionX(actions)
        }
    }
}