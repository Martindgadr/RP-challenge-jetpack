package com.ionnt.rocketpocket.ui.storeshome

import androidx.lifecycle.MutableLiveData
import com.ionnt.rocketpocket.commons.base.BaseViewModel
import com.ionnt.rocketpocket.data.model.Store
import com.ionnt.rocketpocket.repository.StoresRepository
import javax.inject.Inject

/**
 * Created by Martin De Girolamo on 05/04/2019.
 */

class StoresViewModel @Inject constructor(private val repository: StoresRepository) : BaseViewModel() {
    var storesGetted: MutableLiveData<List<Store>> = MutableLiveData()

    fun getStoresData(forceNetwork: Boolean) {
        repository.getStores(forceNetwork, ::handleSuccess, ::handleFailure)
    }

    private fun handleSuccess(stores: List<Store>){
        storesGetted.value = stores
    }

    override fun cancelRequest() = repository.cancelJob()
}