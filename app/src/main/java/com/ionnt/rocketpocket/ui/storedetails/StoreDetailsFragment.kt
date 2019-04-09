package com.ionnt.rocketpocket.ui.storedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ionnt.rocketpocket.R
import com.ionnt.rocketpocket.commons.extensions.loadFromUrl
import com.ionnt.rocketpocket.data.model.Store
import kotlinx.android.synthetic.main.store_details_fragment.*

/**
 * Created by Martin De Girolamo on 05/04/2019.
 */
class StoreDetailsFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.store_details_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val safeArgs = StoreDetailsFragmentArgs.fromBundle(it)
            updateDataScreen(safeArgs.storeArg)
        }
    }

    private fun updateDataScreen(storeData: Store) {
        storeLogoImageView.loadFromUrl(storeData.logo)
        storeNametextView.text = storeData.name
        addrTextView.text = storeData.address
        cityTextView.text = storeData.city
        zipCodeTextView.text = storeData.zipcode
        stateTextView.text = storeData.state
        phonetextView.text = storeData.phone
    }
}