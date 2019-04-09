package com.ionnt.rocketpocket.data.api

import com.ionnt.rocketpocket.data.dtos.StoresDto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Created by Martin De Girolamo on 05/04/2019.
 */
interface StoresAPI {
    companion object {
        const val BASE_URL = "http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/"
        const val STORES = "stores.json"
    }

    @GET(STORES)
    fun getAllStoresAsync() : Deferred<Response<StoresDto>>

    @GET(STORES)
    @Headers("Cache-Control: no-cache")
    fun getAllStoresNoCacheAsync(): Deferred<Response<StoresDto>>
}