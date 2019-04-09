package com.ionnt.rocketpocket.commons.di.modules

import com.ionnt.rocketpocket.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Martin De Girolamo on 05/04/2019.
 */

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [(FragmentModule::class)])
    abstract fun contributeMainActivity(): MainActivity
}