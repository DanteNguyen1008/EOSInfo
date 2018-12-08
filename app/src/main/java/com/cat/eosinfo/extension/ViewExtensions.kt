package com.cat.eosinfo.extension

import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity

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