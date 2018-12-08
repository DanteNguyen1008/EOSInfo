package com.cat.eosinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cat.eosinfo.repo.model.Block

class BlockItemViewModel : ViewModel() {

    val blockName = MutableLiveData<String>()

    fun bind(block: Block) {
        this.blockName.value = block.id
    }
}