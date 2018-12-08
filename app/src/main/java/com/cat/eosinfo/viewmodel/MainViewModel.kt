package com.cat.eosinfo.viewmodel

import androidx.lifecycle.ViewModel
import com.cat.eosinfo.adapter.BlockAdapter
import com.cat.eosinfo.repo.model.Block
import com.cat.eosinfo.repo.model.ServerInfo
import com.cat.eosinfo.util.SingleLiveEvent
import com.cat.eosinfo.util.Utils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {
    val adapter = BlockAdapter(object : BlockAdapter.BlockViewListener {
        override fun onClicked(block: Block, pos: Int) {
            blockClicked.value = block
        }
    })
    private val compositeDisposable = CompositeDisposable()
    private val okHttpClient = OkHttpClient.Builder()
        .callTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    val scrollTo = SingleLiveEvent<Int>()
    val blockClicked = SingleLiveEvent<Block>()

    companion object {
        val JSON = MediaType.parse("application/json; charset=utf-8")
    }

    /**
     * Sync first 20 blocks
     */
    fun sync() {
        this.adapter.clearData()
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
            .subscribe {
                this.adapter.add(it)
                this.scrollTo.value = this.adapter.itemCount - 1
            })
    }

    override fun onCleared() {
        this.compositeDisposable.clear()
        super.onCleared()
    }
}