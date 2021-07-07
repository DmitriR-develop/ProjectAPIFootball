package com.dmitri.projectapifootball.modules

import com.dmitri.projectapifootball.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}