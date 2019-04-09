package com.ionnt.rocketpocket.commons.di

import com.ionnt.rocketpocket.RPApplication
import com.ionnt.rocketpocket.commons.di.modules.ActivityModule
import com.ionnt.rocketpocket.commons.di.modules.ApplicationModule
import com.ionnt.rocketpocket.commons.di.modules.RepositoryModule
import com.ionnt.rocketpocket.commons.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Martin De Girolamo on 05/04/2019.
 */
@Singleton
@Component(modules = [(ApplicationModule::class), (AndroidInjectionModule::class), (ActivityModule::class),
    (ViewModelModule::class), (RepositoryModule::class)])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: RPApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: RPApplication)
}