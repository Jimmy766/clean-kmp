package com.bimm.takehomeassignmnent.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bimm.takehomeassignmnent.ui.layout.DefaultLayout
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text

@Composable
fun Home(){
  DefaultLayout {
    Scaffold { padding ->
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
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Text(text = shop.name)
      Text(text = " - ${shop.address}")
      Text(text = " - ${"★".repeat(shop.stars)}")
    }
  }
}