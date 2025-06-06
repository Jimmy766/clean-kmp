package com.bimm.takehomeassignmnent

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.bimm.takehomeassignmnent.ui.detail.Detail
import com.bimm.takehomeassignmnent.ui.home.Home
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext


@Serializable
object HomeScreen

@Serializable
class DetailScreen(val id: Int)

@Composable
@Preview
fun App() {
  setSingletonImageLoaderFactory {
    ImageLoader.Builder(it)
      .crossfade(true)
      .logger(DebugLogger())
      .build()
  }
  KoinContext {
      val navController = rememberNavController()
      NavHost(
          navController = navController,
          startDestination = HomeScreen
      ) {
          composable<HomeScreen> {
              Home(
                  onClick = { id -> navController.navigate(DetailScreen(id)) }
              )
          }
          composable<DetailScreen> {
              data ->
                  val screen = data.toRoute<DetailScreen>()
                  Detail(id = screen.id,
                         onBack = { navController.popBackStack() }
                  )
          }
      }
  }
}