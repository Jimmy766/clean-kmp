package com.bimm.takehomeassignmnent

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.bimm.takehomeassignmnent.ui.detail.Detail
import com.bimm.takehomeassignmnent.ui.home.Home
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview


@Serializable
object HomeScreen

@Serializable
class DetailScreen(val name: String)

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeScreen
    ) {
        composable<HomeScreen> {
            Home(
                onClick = { name -> navController.navigate(DetailScreen(name)) }
            )
        }
        composable<DetailScreen> {
            data ->
                val screen = data.toRoute<DetailScreen>()
                Detail(name = screen.name,
                    onBack = { navController.popBackStack() }
                )
        }
    }
}