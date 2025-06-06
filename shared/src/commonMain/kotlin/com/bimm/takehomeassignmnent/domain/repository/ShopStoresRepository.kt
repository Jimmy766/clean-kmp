package com.bimm.takehomeassignmnent.domain.repository

import com.bimm.takehomeassignmnent.domain.models.ShopStore
import kotlinx.coroutines.flow.Flow

interface ShopStoresRepository {
  fun findById(id: Int): Flow<ShopStore>
  fun getAll(): Flow<List<ShopStore>>
  suspend fun save(shopStores: List<ShopStore>)
}