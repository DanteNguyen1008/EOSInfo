package com.cat.eosinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cat.eosinfo.adapter.TransactionAdapter
import com.cat.eosinfo.repo.model.Block
import com.cat.eosinfo.repo.model.Transaction

class DetailViewModel : ViewModel() {
    val producer = MutableLiveData<String>()
    val producerSignature = MutableLiveData<String>()
    val transactionCount = MutableLiveData<String>()
    val transactionDataLoad = MutableLiveData<List<Transaction>>()

    /**
     * Load data from previous screen
     *
     * @param block Block
     */
    fun loadData(block: Block?) {
        if (block == null) {
            return
        }

        this.producer.value = block.producer
        this.producerSignature.value = block.producer_signature

        if (block.transactions != null) {
            this.transactionCount.value = block.transactions!!.size.toString()
        }

        if (block.transactions != null) {
            this.transactionDataLoad.value = block.transactions
        }
    }
}