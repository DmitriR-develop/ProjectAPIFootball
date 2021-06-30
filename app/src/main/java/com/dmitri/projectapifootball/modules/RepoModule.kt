package com.dmitri.projectapifootball.modules

import com.dmitri.projectapifootball.api.IDataSource
import com.dmitri.projectapifootball.model.ILeaguesRepo
import com.dmitri.projectapifootball.model.RetrofitLeaguesRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun leaguesRepo(api: IDataSource): ILeaguesRepo = RetrofitLeaguesRepo(api)
}