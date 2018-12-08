package com.cat.eosinfo.util

import com.cat.eosinfo.repo.model.Block
import com.cat.eosinfo.repo.model.ServerInfo
import com.cat.eosinfo.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class Utils {
    companion object {
        @JvmStatic
        fun requestServerInfo(okHttpClient: OkHttpClient): ServerInfo {
            val request = Request
                .Builder()
                .url("https://api.eosnewyork.io/v1/chain/get_info")
                .post(RequestBody.create(MainViewModel.JSON, "{}"))

            return ServerInfo.fromJSON(okHttpClient.newCall(request.build()).execute().body()!!.string())
        }

        @JvmStatic
        fun requestBlock(okHttpClient: OkHttpClient, blockId: String): Block {
            val request = Request
                .Builder()
                .url("https://api.eosnewyork.io/v1/chain/get_block")
                .post(RequestBody.create(MainViewModel.JSON, "{\"block_num_or_id\": \"$blockId\"}"))
            return Block.fromJSON(okHttpClient.newCall(request.build()).execute().body()!!.string())
        }
    }
}
