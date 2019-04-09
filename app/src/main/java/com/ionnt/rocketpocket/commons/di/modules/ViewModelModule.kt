package com.ionnt.rocketpocket.commons.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ionnt.rocketpocket.commons.di.annotations.ViewModelKey
import com.ionnt.rocketpocket.commons.factory.ViewModelFactory
import com.ionnt.rocketpocket.ui.storeshome.StoresViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Martin De Girolamo on 05/04/2019.
 */

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(StoresViewModel::class)
    abstract fun bindsStoresViewModel(storesViewModel: StoresViewModel): ViewModel
}