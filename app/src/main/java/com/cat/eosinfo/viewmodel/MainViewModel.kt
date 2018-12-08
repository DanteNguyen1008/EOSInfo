package com.cat.eosinfo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.cat.eosinfo.adapter.BlockAdapter
import com.cat.eosinfo.repo.model.ServerInfo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {
    val adapter = BlockAdapter()
    val compositeDisposable = CompositeDisposable()

    fun loadData() {
        val data = ArrayList(arrayOf("Block 1", "Block 2").toList())
        this.adapter.notifyDataSetChanged(data)
    }

    fun sync() {
        // Start WorkManager
//        Observable.range(0, 20)
        this.compositeDisposable.add(Observable.create<JSONObject> {
                val okHttpClient = OkHttpClient.Builder()
                    .callTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build()

                val request = Request
                    .Builder()
                    .url("https://api.eosnewyork.io/v1/chain/get_info")
                    .post(RequestBody.create(null, "application/json"))

                val rawResult = okHttpClient.newCall(request.build()).execute().body()!!.string()
                it.onNext(JSONObject(rawResult))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Sync", "ServerInfo $it")
            })
    }

    override fun onCleared() {
        this.compositeDisposable.clear()
        super.onCleared()
    }
}