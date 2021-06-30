package com.dmitri.projectapifootball.modules

import com.dmitri.projectapifootball.fragments.LeaguesFragment
import com.dmitri.projectapifootball.fragments.TeamsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {
    @ContributesAndroidInjector
    abstract fun bindLeaguesFragment(): LeaguesFragment

    @ContributesAndroidInjector
    abstract fun bindTeamsFragment(): TeamsFragment
}