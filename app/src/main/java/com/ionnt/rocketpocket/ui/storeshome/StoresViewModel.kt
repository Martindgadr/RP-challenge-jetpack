package com.ionnt.rocketpocket.ui.storeshome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ionnt.rocketpocket.commons.base.BaseViewModel
import com.ionnt.rocketpocket.data.model.Store
import com.ionnt.rocketpocket.repository.StoresRepository
import javax.inject.Inject

/**
 * Created by Martin De Girolamo on 05/04/2019.
 */

class StoresViewModel @Inject constructor(private val repository: StoresRepository) : BaseViewModel() {
    val storesGetted: LiveData<List<Store>> get() = storesGettedRepo
    private val storesGettedRepo: MutableLiveData<List<Store>> = MutableLiveData()

    init {
        getStoresData(false)
    }

    fun getStoresData(forceNetwork: Boolean) {
        repository.getStores(forceNetwork, ::handleSuccess, ::handleFailure)
    }

    private fun handleSuccess(stores: List<Store>){
        storesGettedRepo.value = stores
    }

    override fun cancelRequest() = repository.cancelJob()
}