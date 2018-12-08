package com.cat.eosinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BlockItemViewModel : ViewModel() {

    val blockName = MutableLiveData<String>()

    fun bind(value: String) {
        this.blockName.value = value
    }
}