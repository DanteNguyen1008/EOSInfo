package com.cat.eosinfo.repo.model

import android.os.Parcelable
import com.cat.eosinfo.extension.getStringSafe
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Data(
    val from: String,
    val memo: String,
    val quantity: String,
    val to: String
) : Parcelable {
    companion object {

        @JvmStatic
        fun fromJSON(jsonObject: JSONObject) = Data(
            jsonObject.getStringSafe("from"),
            jsonObject.getStringSafe("memo"),
            jsonObject.getStringSafe("quantity"),
            jsonObject.getStringSafe("to")
        )
    }
}