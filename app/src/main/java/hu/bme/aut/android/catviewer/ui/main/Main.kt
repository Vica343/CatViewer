package hu.bme.aut.android.catviewer.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.ProvideWindowInsets

// TODO

@Composable
fun CatMainScreen() {
    val navController = rememberNavController()

    ProvideWindowInsets {
        NavHost(navController = navController, startDestination = NavScreen.Home.route) {
            composable(NavScreen.Home.route) {
                Cats(
                    viewModel = hiltViewModel()
                )
            }
        }
    }
}


sealed class NavScreen (val route: String) {

    object Home : NavScreen("Home")

    object About : NavScreen("About")
}