package com.cat.eosinfo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cat.eosinfo.R
import com.cat.eosinfo.databinding.ItemBlockBinding
import com.cat.eosinfo.viewmodel.BlockItemViewModel
import kotlinx.android.extensions.LayoutContainer

class BlockAdapter : RecyclerView.Adapter<BlockAdapter.ViewHolder>() {

    private val data = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_block, parent, false),
            BlockItemViewModel()
        )
    }

    override fun getItemCount() = this.data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.data[position])
    }

    fun notifyDataSetChanged(data: ArrayList<String>) {
        // TODO Should use add single, but this for now
        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemBlockBinding,
        val viewModel: BlockItemViewModel
    ) : RecyclerView.ViewHolder(binding.root), LayoutContainer {

        override val containerView: View?
            get() = this.binding.root

        fun bind(value: String) {
            this.binding.viewModel = viewModel
            this.viewModel.bind(value)
        }
    }
}