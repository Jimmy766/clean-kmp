package com.bimm.takehomeassignmnent.ui.layout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DefaultLayout(
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit
) {
    MaterialTheme{
      Surface(modifier = Modifier.fillMaxSize(), content = content)
    }
}