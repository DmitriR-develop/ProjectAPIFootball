package com.dmitri.projectapifootball

import android.os.Bundle
import com.dmitri.projectapifootball.R.layout.activity_main
import com.dmitri.projectapifootball.abs.AbsActivity
import com.dmitri.projectapifootball.navigation.AndroidScreen
import com.dmitri.projectapifootball.navigation.IBackButtonListener
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity() : AbsActivity(activity_main) {

    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState ?: router.newRootScreen(AndroidScreen.LeaguesScreens())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is IBackButtonListener && it.backPressed()) {
                return
            }
        }
    }
}