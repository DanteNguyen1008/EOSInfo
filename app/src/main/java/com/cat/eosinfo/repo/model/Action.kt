package com.cat.eosinfo.repo.model

import android.os.Parcelable
import com.cat.eosinfo.extension.*
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Action(
    val account: String,
    val authorization: List<Authorization>,
    val data: Data?,
    val dataStr: String?,
    val name: String
) : Parcelable {
    companion object {

        @JvmStatic
        fun fromJSON(jsonObject: JSONObject): Action {
            val authorizationArr = jsonObject.getJSONArraySafe("authorization")
            val authorization = ArrayList<Authorization>()

            if (authorizationArr != null) {
                authorization.addAll(
                    ArrayList<Authorization>(
                        authorizationArr.toList()
                            .map {
                                Authorization.fromJSON(it as JSONObject)
                            })
                )
            }

            var data: Data? = null
            var dataStr: String? = null

            val rawData = jsonObject.getSafe("data")
            if (rawData is String) {
                dataStr = rawData
            } else if (rawData is JSONObject) {
                data = Data.fromJSON(rawData)
            }

            return Action(
                jsonObject.getStringSafe("account"),
                authorization,
                data,
                dataStr,
                jsonObject.getStringSafe("name")
            )
        }
    }
}