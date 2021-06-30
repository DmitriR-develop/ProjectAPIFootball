package com.dmitri.projectapifootball.presenter

import com.dmitri.projectapifootball.navigation.AndroidScreen
import com.dmitri.projectapifootball.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreen.LeaguesScreens().getFragment())
    }

    fun backClicked() {
        router.exit()
    }
}