package com.dmitri.projectapifootball.navigation

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun getFragment(): Screen
}