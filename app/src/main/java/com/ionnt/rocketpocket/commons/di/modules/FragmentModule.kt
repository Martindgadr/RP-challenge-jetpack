package com.ionnt.rocketpocket.commons.di.modules

import com.ionnt.rocketpocket.commons.di.annotations.FragmentScope
import com.ionnt.rocketpocket.ui.storeshome.StoresFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Martin De Girolamo on 05/04/2019.
 */

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributeMoviesFragment(): StoresFragment
}