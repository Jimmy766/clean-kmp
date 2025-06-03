package com.bimm.takehomeassignmnent.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(){
  val scroller = TopAppBarDefaults.pinnedScrollBehavior()
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
      LazyColumn (
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(padding)
      ) {
        items(items = stores, key = { it.id }) { store ->
          ShopItem(shop = store)
        }
      }
    }
  }
}

@Composable
fun ShopItem(shop: Shop){
  Column {
    Box(
      modifier = Modifier.fillMaxWidth()
        .clip(MaterialTheme.shapes.medium)
        .background(MaterialTheme.colorScheme.primary)
    ){
      Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
      ) {
        Text(text = shop.name)
        Text(text = " - ${shop.address}")
        Text(text = " - ${"★".repeat(shop.stars)}")
      }
    }
  }
}