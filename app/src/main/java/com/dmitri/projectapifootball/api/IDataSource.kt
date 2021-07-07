package com.dmitri.projectapifootball.api

import com.dmitri.projectapifootball.model.Leagues
import com.dmitri.projectapifootball.model.Teams
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IDataSource {

    @GET("v3/leagues")
    fun getLeagues(): Single<List<Leagues>>

    @GET("v3/leagues?id={query}")
    fun getLeaguesById(@Path("query") id: Int): Single<List<Leagues>>

    @GET("teams?id={query}")
    fun getTeams(@Path("query") name: Int): Single<List<Teams>>
}