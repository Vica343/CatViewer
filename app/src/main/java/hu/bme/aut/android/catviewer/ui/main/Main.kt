package hu.bme.aut.android.catviewer.ui.main

// TODO

sealed class NavScreen (val route: String) {

    object Home : NavScreen("Home")

    object About : NavScreen("About")
}