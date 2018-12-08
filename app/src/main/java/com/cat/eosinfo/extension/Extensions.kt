package com.cat.eosinfo.extension

import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject

/**
 * Getting parent activity of View
 *
 * @receiver View
 * @return AppCompatActivity
 */
fun View.getParentAcitivity() : AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }

        context = context.baseContext
    }

    return null
}

/**
 * Convert JSON Array to List
 *
 * @receiver JSONArray
 * @return ArrayList<Any>
 */
fun JSONArray.toList() : ArrayList<Any> {
    val result = ArrayList<Any>()
    if (this.length() > 0) {
        var i = 0
        while (i < this.length()) {
            result.add(this[i])
            i++
        }
    }

    return result
}

/**
 * Safely get String
 *
 * @receiver JSONObject
 * @param name String
 * @return String
 */
fun JSONObject.getStringSafe(name : String) : String {
    if (this.has(name)) {
        return this.getString(name)
    }

    return ""
}

/**
 * Safely get Int
 *
 * @receiver JSONObject
 * @param name String
 * @return Int
 */
fun JSONObject.getIntSafe(name : String) : Int {
    if (this.has(name)) {
        return this.getInt(name)
    }

    return -1
}

/**
 * Safely get JSONObject
 *
 * @receiver JSONObject
 * @param name String
 * @return JSONObject?
 */
fun JSONObject.getJSONObjectSafe(name : String) : JSONObject? {
    if (this.has(name)) {
        return this.getJSONObject(name)
    }

    return null
}

/**
 * Safely get JSONArray
 *
 * @receiver JSONObject
 * @param name String
 * @return JSONArray?
 */
fun JSONObject.getJSONArraySafe(name : String) : JSONArray? {
    if (this.has(name)) {
        return this.getJSONArray(name)
    }

    return null
}

/**
 * Safely get (Any)
 * @receiver JSONObject
 * @param name String
 * @return Any?
 */
fun JSONObject.getSafe(name : String) : Any? {
    if (this.has(name)) {
        return this.get(name)
    }

    return null
}