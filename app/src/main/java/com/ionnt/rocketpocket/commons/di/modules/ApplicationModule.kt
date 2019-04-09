package com.ionnt.rocketpocket.commons.di.modules

import android.content.Context
import com.ionnt.rocketpocket.BuildConfig
import com.ionnt.rocketpocket.RPApplication
import com.ionnt.rocketpocket.data.api.StoresAPI
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Martin De Girolamo on 05/04/2019.
 */

@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun provideApplicationContext(application: RPApplication): Context = application

    @Provides @Singleton fun provideRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(StoresAPI.BASE_URL)
            .client(createClient(context))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(context: Context): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        val cacheSize = 10 * 1024 * 1024 // 10 MB cache size

        val httpCacheDirectory = File(context.cacheDir, "http-cache")
        val cache = Cache(httpCacheDirectory, cacheSize.toLong())

        val networkCacheInterceptor = Interceptor { chain ->
            val response = chain.proceed(chain.request())

            val cacheControl = CacheControl.Builder()
                .maxAge(1, TimeUnit.MINUTES) // 1 Minute life, after that will call network again
                .build()

            response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }

        return okHttpClientBuilder
            .addNetworkInterceptor(networkCacheInterceptor)
            .cache(cache)
            .build()
    }
}