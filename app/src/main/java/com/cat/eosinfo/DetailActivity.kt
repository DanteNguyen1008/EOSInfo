package com.cat.eosinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.cat.eosinfo.databinding.ActivityDetailBinding
import com.cat.eosinfo.viewmodel.DetailViewModel
import com.cat.eosinfo.viewmodel.providers.DetailViewModelFactory

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_SELECTED_BLOCK_DETAIL_VIEW = "extra.selected.block.detail.view"
    }

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
    }

    override fun onStart() {
        super.onStart()
        this.binding.viewModel = ViewModelProviders.of(this, DetailViewModelFactory(this.intent.extras)).get(DetailViewModel::class.java)
    }
}