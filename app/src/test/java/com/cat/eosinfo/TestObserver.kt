package com.cat.eosinfo

import androidx.lifecycle.Observer

class TestObserver<T> : Observer<T> {
    val observableValues = mutableListOf<T?>()

    override fun onChanged(t: T?) {
        observableValues.add(t)
    }
}