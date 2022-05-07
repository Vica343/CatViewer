package hu.bme.aut.android.catviewer.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import hu.bme.aut.android.catviewer.ui.about.About

@Composable
fun CatMainScreen() {
    val navController = rememberNavController()

    ProvideWindowInsets {
        NavHost(navController = navController, startDestination = NavScreen.Home.route) {
            composable(NavScreen.Home.route) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "CatViewer") },
                            actions = {
                                IconButton(
                                    onClick = {
                                        navController.navigate(NavScreen.About.route)
                                    },
                                    modifier = Modifier.padding(
                                        horizontal = 20.dp
                                    )
                                )
                                {
                                    Icon(
                                        imageVector = Icons.Rounded.Info,
                                        contentDescription = "Icon",
                                        tint = Color.White
                                    )
                                }
                            }
                        )
                    },
                    content = {

                        Spacer(modifier = Modifier.height(20.dp))
                        Cats(
                            viewModel = hiltViewModel()
                        )
                    })
            }
            composable(
                route = NavScreen.About.route
            ) {

                About(viewModel = hiltViewModel()) {
                    navController.navigateUp()
                }

            }
        }
    }
}


sealed class NavScreen(val route: String) {

    object Home : NavScreen("Home")

    object About : NavScreen("About")
}