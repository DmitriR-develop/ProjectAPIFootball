package com.dmitri.projectapifootball.model

import com.dmitri.projectapifootball.api.IDataSource
import io.reactivex.rxjava3.core.Single

class RetrofitLeaguesRepo(private val api: IDataSource) : ILeaguesRepo {

    override fun getLeagues() = api.getLeagues()

    override fun getLeaguesById(id: Int): Single<List<Leagues>> = api.getLeaguesById(id)

    override fun getTeams(team: Int): Single<List<Teams>> = api.getTeams(team)

}