package com.bimm.takehomeassignmnent.domain.repository

import com.bimm.takehomeassignmnent.domain.models.ShopStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull

class FakeRepository: ShopStoresRepository {

  val flow = MutableStateFlow<List<ShopStore>>(emptyList())

  override fun findById(id: Int): Flow<ShopStore> {
    return flow.mapNotNull { list -> list.find { it.id == id } }
  }

  override fun getAll(): Flow<List<ShopStore>> = flow

  override suspend fun save(shopStores: List<ShopStore>) {
    flow.value += shopStores
  }
}