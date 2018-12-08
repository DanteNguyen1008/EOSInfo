package com.cat.eosinfo.repo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Authorization(
    val actor: String,
    val permission: String
) : Parcelable {
    companion object {

        @JvmStatic
        fun fromJSON(jsonObject: JSONObject) = Authorization(
            jsonObject.getString("actor"),
            jsonObject.getString("permission")
        )
    }
}