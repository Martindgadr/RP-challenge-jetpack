package com.ionnt.rocketpocket.commons.di.modules

import com.ionnt.rocketpocket.repository.StoresRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Martin De Girolamo on 06/04/2019.
 */

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providesStoresRepository(sourceRepository: StoresRepository.StoresRepositoryImpl): StoresRepository = sourceRepository
}