package com.ionnt.rocketpocket.data.api

import com.ionnt.rocketpocket.commons.utils.Failure
import com.ionnt.rocketpocket.data.dtos.StoresDto
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Martin De Girolamo on 06/04/2019.
 */
@Singleton
class StoresService @Inject constructor(retrofit: Retrofit) {
    private val newsApi by lazy { retrofit.create(StoresAPI::class.java) }

    suspend fun getStores(forceNetwork: Boolean, success: (stores: StoresDto) -> Unit, error: (failure: Failure) -> Unit)
    {
        try {
            val response: Response<StoresDto> = if (forceNetwork)
                newsApi.getAllStoresAsync("no-cache").await()
            else
                newsApi.getAllStoresAsync(null).await()

            if (response.isSuccessful) {
                response.body()?.let {
                    success(it)
                }?: error(Failure.NoDataAvailable)
            } else
                error(Failure.ServerError)
        } catch (e: Exception) {
            error(Failure.ServerError)
        }
    }
}