package com.bimm.takehomeassignmnent.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bimm.takehomeassignmnent.ui.layout.DefaultLayout
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.bimm.takehomeassignmnent.domain.models.ShopStore
import com.bimm.takehomeassignmnent.ui.common.Loading
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
  onClick: (Int) -> Unit,
  viewModel: HomeViewModel = koinViewModel()
) {

  val scroller = TopAppBarDefaults.pinnedScrollBehavior()
  viewModel.fetchShops()

  DefaultLayout {
    Scaffold(
      topBar = {
        TopAppBar(
          title = { Text(text = "Shops")},
          scrollBehavior = scroller
        )
      },
      modifier = Modifier.nestedScroll(scroller.nestedScrollConnection)
    ) { padding ->
      val stores by viewModel.observable.collectAsState()
      Loading(stores.loading, modifier = Modifier.padding(padding))
      LazyColumn (
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(padding)
      ) {
        items(items = stores.shops, key = { it.id!! }) { store ->
          ShopItem(shop = store, onClick)
        }
      }
    }
  }
}

@Composable
fun ShopItem(shop: ShopStore, onClick: (Int) -> Unit){
  Column {
    Box(
      modifier = Modifier.fillMaxWidth()
        .clip(MaterialTheme.shapes.medium)
        .background(MaterialTheme.colorScheme.primary)
        .clickable {
          onClick(shop.id!!)
        }
    ){
      Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly
      ) {
        Text(text = shop.name)
        Text(text = " - ${shop.address}")
        Text(text = " - ${"★".repeat(shop.rating.roundToInt())}")
      }
    }
  }
}