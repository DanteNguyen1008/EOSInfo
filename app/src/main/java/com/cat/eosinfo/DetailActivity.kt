package com.cat.eosinfo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cat.eosinfo.databinding.ActivityDetailBinding
import com.cat.eosinfo.viewmodel.DetailViewModel
import com.cat.eosinfo.viewmodel.providers.DetailViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

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
        rl_transaction_list.layoutManager = LinearLayoutManager(this)
        btn_toggle_transaction.setOnClickListener { this.toggleTransactionList() }
    }

    private fun toggleTransactionList() {
        if (rl_transaction_list.visibility == View.VISIBLE) {
            rl_transaction_list.visibility = View.GONE
        } else {
            rl_transaction_list.visibility = View.VISIBLE
        }
    }
}