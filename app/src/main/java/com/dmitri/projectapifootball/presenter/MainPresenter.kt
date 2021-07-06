package com.dmitri.projectapifootball.presenter

import com.dmitri.projectapifootball.navigation.AndroidScreen
import com.dmitri.projectapifootball.view.MainView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreen.LeaguesScreens())
    }

    fun backClicked() {
        router.exit()
    }
}