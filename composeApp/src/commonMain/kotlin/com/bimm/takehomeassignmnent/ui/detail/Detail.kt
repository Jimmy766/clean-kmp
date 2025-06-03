package com.bimm.takehomeassignmnent.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bimm.takehomeassignmnent.ui.home.Shop
import com.bimm.takehomeassignmnent.ui.home.stores
import com.bimm.takehomeassignmnent.ui.layout.DefaultLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Detail(name: String, onBack: () -> Unit) {
  val shop = stores.random()
  val scroller = TopAppBarDefaults.pinnedScrollBehavior()
  DefaultLayout {
    Scaffold(
      topBar = { DetailTopBar(shop, scroller, onBack) }
    ) {padding ->
      Column(
        modifier = Modifier.fillMaxSize()
          .padding(padding)
          .verticalScroll(rememberScrollState())
      ) {
        AsyncImage(
          model = shop.imageUrl,
          contentDescription = "Shop Image",
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxWidth()
            .aspectRatio(16f / 9f)
        )
        Text(
          text = "★".repeat(shop.stars),
          modifier = Modifier.padding(8.dp).align(Alignment.End)
        )
        Text(
          modifier = Modifier.padding(8.dp),
          text = shop.description
        )
        Text(
          modifier = Modifier.padding(vertical = 20.dp, horizontal = 8.dp),
          text = "Address: ${shop.address}",
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { /* TODO */ },
               modifier = Modifier.padding(8.dp)
                 .align(Alignment.End)
        ) {
          Text(text = "Visit Website")
        }
      }
    }
  }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailTopBar(
  shop: Shop,
  scroller: TopAppBarScrollBehavior,
  onBack: () -> Unit
) {
  TopAppBar(
    title = { Text(shop.name, style = MaterialTheme.typography.headlineMedium) },
    navigationIcon = {
      IconButton(onClick = { onBack() }) {
        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
      }
    },
    scrollBehavior = scroller
  )
}