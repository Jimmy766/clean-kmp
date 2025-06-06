package com.bimm.takehomeassignmnent.ui.detail

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bimm.takehomeassignmnent.domain.models.ShopStore
import com.bimm.takehomeassignmnent.ui.common.Loading
import com.bimm.takehomeassignmnent.ui.layout.DefaultLayout
import com.bimm.takehomeassignmnent.utils.ExternalLink
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Detail(
  id: Int,
  onBack: () -> Unit,
  detailViewModel: DetailViewModel = koinViewModel()
) {
  val linkOpener: ExternalLink = koinInject()

  val scroller = TopAppBarDefaults.pinnedScrollBehavior()
  detailViewModel.getShop(id)
  val shop by detailViewModel.observable.collectAsState()
  DefaultLayout {
    Scaffold(
      topBar = { shop.store?.let { DetailTopBar(it, scroller, onBack) } }
    ) {padding ->
      Loading(shop.loading, modifier = Modifier.padding(padding))
      if(!shop.loading) {
        Column(
          modifier = Modifier.fillMaxSize()
            .padding(padding)
            .verticalScroll(rememberScrollState())
        ) {
          AsyncImage(
            model = shop.store?.picture?:"https://static.vecteezy.com/system/resources/thumbnails/008/255/803/small/page-not-found-error-404-system-updates-uploading-computing-operation-installation-programs-system-maintenance-a-hand-drawn-layout-template-of-a-broken-robot-illustration-vector.jpg",
            contentDescription = "Shop Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
              .aspectRatio(16f / 9f)
          )
          Text(
            text = shop.store?.rating?.let { "★".repeat(it.roundToInt()) } ?: "",
            modifier = Modifier.padding(8.dp).align(Alignment.End)
          )
          Text(
            modifier = Modifier.padding(8.dp),
            text = shop.store?.description ?: "No description available.",
          )
          Text(
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 8.dp)
              .clickable { shop.store?.let { linkOpener.openLink(it.google_maps_link) } },
            text = "Address: ${shop.store?.address}",
            fontStyle = FontStyle.Italic,
          )
          Spacer(modifier = Modifier.weight(1f))
          Button(
            onClick = { shop.store?.let { linkOpener.openLink(it.website) } },
            modifier = Modifier.padding(8.dp)
              .align(Alignment.End)
          ) {
            Text(text = "Visit Website")
          }
        }
      }
    }
  }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailTopBar(
  shop: ShopStore,
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