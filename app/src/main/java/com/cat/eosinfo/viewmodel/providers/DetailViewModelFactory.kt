package com.cat.eosinfo.viewmodel.providers

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cat.eosinfo.DetailActivity
import com.cat.eosinfo.repo.model.Block
import com.cat.eosinfo.viewmodel.DetailViewModel

class DetailViewModelFactory(val bundle : Bundle?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var block : Block? = null

        if (bundle != null) {
            block = bundle.getParcelable(DetailActivity.EXTRA_SELECTED_BLOCK_DETAIL_VIEW)
        }

        val viewModel = DetailViewModel()
        viewModel.loadData(block)
        return viewModel as T
    }
}