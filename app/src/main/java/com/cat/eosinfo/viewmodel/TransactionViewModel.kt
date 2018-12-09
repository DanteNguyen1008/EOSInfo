package com.cat.eosinfo.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cat.eosinfo.repo.model.Transaction

class TransactionViewModel : ViewModel() {
    val transactionTime = MutableLiveData<String>()
    val transactionFrom = MutableLiveData<String>()
    val transactionTo = MutableLiveData<String>()
    val transactionAmount = MutableLiveData<String>()
    val trxStr = MutableLiveData<String>()
    var fullInfoVisibility = View.GONE
    var trxStrVisibility = View.GONE

    fun bind(transaction: Transaction) {
        if (transaction.trx?.transaction != null) {
            this.transactionTime.value = transaction.trx.transaction.expiration
            if (transaction.trx.transaction.actions.isNotEmpty()) {
                this.transactionFrom.value = transaction.trx.transaction.actions[0].data?.from
                this.transactionTo.value = transaction.trx.transaction.actions[0].data?.to
                this.transactionAmount.value = transaction.trx.transaction.actions[0].data?.quantity
            }

            fullInfoVisibility = View.VISIBLE
            trxStrVisibility = View.GONE
        } else if (transaction.trxStr != null) {
            this.trxStr.value = transaction.trxStr
            fullInfoVisibility = View.GONE
            trxStrVisibility = View.VISIBLE
        }
    }
}