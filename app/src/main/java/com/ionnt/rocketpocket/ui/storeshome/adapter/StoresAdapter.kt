package com.ionnt.rocketpocket.ui.storeshome.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ionnt.rocketpocket.R
import com.ionnt.rocketpocket.commons.extensions.inflate
import com.ionnt.rocketpocket.data.model.Store

/**
 * Created by Martin De Girolamo on 06/04/2019.
 */

class StoresAdapter(private var list: List<Store>): RecyclerView.Adapter<StoresViewHolder>() {
    internal var onClickAction: ((Store) -> Unit) = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoresViewHolder =
        StoresViewHolder(parent.inflate(R.layout.store_cardview_item))

    override fun onBindViewHolder(holder: StoresViewHolder, position: Int) =
        holder.bind(list[position], onClickAction)

    override fun getItemCount(): Int = list.size

    fun setListData(list: List<Store>) {
        this.list = list
        notifyDataSetChanged()
    }
}