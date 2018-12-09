package com.cat.eosinfo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cat.eosinfo.repo.model.Block
import com.cat.eosinfo.util.Utils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val okHttpClient = OkHttpClient.Builder()
        .callTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    val newBlockLiveData = MutableLiveData<Block>()

    companion object {
        val JSON = MediaType.parse("application/json; charset=utf-8")
    }

    /**
     * Sync first 20 blocks
     */
    fun sync() {
        // Start WorkManager
        this.compositeDisposable.add(Observable.create<Block> {
            val serverInfo = Utils.requestServerInfo(okHttpClient)
            val listOfBlock = Block.empty20Items()
            var prevBlockId = serverInfo.head_block_id
            listOfBlock.forEach { block ->
                val fullBlockData = Utils.requestBlock(okHttpClient, prevBlockId)
                block.id = fullBlockData.id
                block.block_num = fullBlockData.block_num
                block.previous = fullBlockData.previous
                block.producer = fullBlockData.producer
                block.producer_signature = fullBlockData.producer_signature
                block.transactions = fullBlockData.transactions

                prevBlockId = block.previous!!
                it.onNext(block)
            }

            it.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ block ->
                newBlockLiveData.value = block
            }, { error ->
                Log.e("Error", error.message)
            })
        )
    }

    override fun onCleared() {
        this.compositeDisposable.clear()
        super.onCleared()
    }
}