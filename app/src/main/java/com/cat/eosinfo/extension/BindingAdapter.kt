package com.cat.eosinfo.extension

import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

/**
 * Binding adapter to set adapter to RecylcerView
 * @param recyclerView RecyclerView
 * @param adapter RecyclerView.Adapter<VH>
 */
@BindingAdapter("recyclerAdapter")
fun <VH : RecyclerView.ViewHolder> recyclerAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<VH>) {
    recyclerView.adapter = adapter
}

/**
 * Binding adapter for TextView with Live data string
 *
 * @param textView AppCompatTextView
 * @param liveData LiveData<String>?
 */
@BindingAdapter("textViewLiveData")
fun textViewLiveData(textView: AppCompatTextView, liveData: LiveData<String>?) {
    val parentActivity = textView.getParentAcitivity()
    if (parentActivity != null && liveData != null) {
        liveData.observe(parentActivity, Observer {
            if (it == null || it == "") {
                return@Observer
            }

            textView.text = it
        })
    }
}