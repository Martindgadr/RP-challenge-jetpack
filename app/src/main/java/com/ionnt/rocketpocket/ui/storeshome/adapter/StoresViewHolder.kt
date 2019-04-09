package com.ionnt.rocketpocket.ui.storeshome.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ionnt.rocketpocket.commons.extensions.loadFromUrl
import com.ionnt.rocketpocket.data.model.Store
import kotlinx.android.synthetic.main.store_cardview_item.view.*

/**
 * Created by Martin De Girolamo on 06/04/2019.
 */
class StoresViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(store: Store?, clickListener: (Store) -> Unit) {
        store?.let { item ->
            itemView.logoImage.loadFromUrl(item.logo)
            itemView.storeNameTextView.text = item.name
            itemView.addressTextView.text = item.address
            itemView.phoneTextView.text = item.phone
            itemView.setOnClickListener{ clickListener(item) }
        }
    }
}