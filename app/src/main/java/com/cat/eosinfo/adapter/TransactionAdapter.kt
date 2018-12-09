package com.cat.eosinfo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cat.eosinfo.R
import com.cat.eosinfo.databinding.ItemTransactionBinding
import com.cat.eosinfo.repo.model.Transaction
import com.cat.eosinfo.viewmodel.TransactionViewModel
import kotlinx.android.extensions.LayoutContainer

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    private val data = ArrayList<Transaction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_transaction, parent, false)
            , TransactionViewModel()
        )
    }

    override fun getItemCount() = this.data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.data[position])
    }

    fun notifyDataSetChanged(data : ArrayList<Transaction>) {
        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemTransactionBinding,
        private val viewModel: TransactionViewModel
    ) : RecyclerView.ViewHolder(binding.root), LayoutContainer {
        override val containerView: View?
            get() = this.binding.root

        fun bind(transaction: Transaction) {
            this.binding.viewModel = this.viewModel
            this.viewModel.bind(transaction)
        }
    }
}