package com.cat.eosinfo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cat.eosinfo.R
import com.cat.eosinfo.databinding.ItemBlockBinding
import com.cat.eosinfo.repo.model.Block
import com.cat.eosinfo.viewmodel.BlockItemViewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_block.view.*

class BlockAdapter(private val listener: BlockViewListener) : RecyclerView.Adapter<BlockAdapter.ViewHolder>() {

    private val data = ArrayList<Block>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_block, parent, false),
            BlockItemViewModel(),
            listener
        )
    }

    override fun getItemCount() = this.data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.data[position], position)
    }

    /**
     * Add new item and refresh data
     *
     * @param block Block?
     */
    fun add(block: Block?) {
        if (block == null) {
            return
        }

        this.data.add(block)
        this.notifyItemInserted(this.data.size - 1)
    }

    fun clearData() {
        this.data.clear()
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemBlockBinding,
        private val viewModel: BlockItemViewModel,
        private val listener: BlockViewListener
    ) : RecyclerView.ViewHolder(binding.root), LayoutContainer {

        override val containerView: View?
            get() = this.binding.root

        fun bind(block: Block, pos: Int) {
            this.binding.root.cv_block.setOnClickListener { listener.onClicked(block, pos) }
            this.binding.viewModel = viewModel
            this.viewModel.bind(block)
        }
    }

    /**
     * Listener to handle block view action
     */
    interface BlockViewListener {

        /**
         * When a block get clicked
         *
         * @param block Block
         * @param pos Int
         */
        fun onClicked(block: Block, pos: Int)
    }
}