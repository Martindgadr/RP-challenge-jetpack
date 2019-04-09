package com.ionnt.rocketpocket.repository

import com.ionnt.rocketpocket.commons.utils.Failure
import com.ionnt.rocketpocket.commons.utils.NetworkHandler
import com.ionnt.rocketpocket.data.api.StoresService
import com.ionnt.rocketpocket.data.model.Store
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Martin De Girolamo on 06/04/2019.
 */

interface StoresRepository {
    fun getStores(forceNetwork: Boolean, success: (stores: List<Store>) -> Unit, error: (failure: Failure) -> Unit)
    fun cancelJob()

    class StoresRepositoryImpl @Inject constructor(private val networkHandler: NetworkHandler,
                                                   private val service: StoresService): StoresRepository {
        private val mainJob = Job()
        private val uiScope = CoroutineScope(Dispatchers.Main + mainJob)

        override fun getStores(forceNetwork: Boolean, success: (stores: List<Store>) -> Unit,
                               error: (failure: Failure) -> Unit) {
            when(networkHandler.isConnected) {
                true -> uiScope.launch {
                    service.getStores(
                        forceNetwork = forceNetwork,
                        success = { success(it.stores) },
                        error = { error(it) }
                    )
              }
                false, null -> error(Failure.NetworkConnection)
            }
        }

        override fun cancelJob() = mainJob.cancel()
    }

}