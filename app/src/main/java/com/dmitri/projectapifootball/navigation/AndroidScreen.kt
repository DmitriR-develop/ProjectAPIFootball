package com.dmitri.projectapifootball.navigation

import com.dmitri.projectapifootball.fragments.LeaguesFragment
import com.dmitri.projectapifootball.fragments.TeamsFragment
import com.dmitri.projectapifootball.model.Leagues
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreen {

    class LeaguesScreens : SupportAppScreen() {
        override fun getFragment() = LeaguesFragment.newInstance()
    }

    class TeamsScreens(private val team: Leagues) : SupportAppScreen() {
        override fun getFragment() = TeamsFragment.newInstance(team)
    }
}