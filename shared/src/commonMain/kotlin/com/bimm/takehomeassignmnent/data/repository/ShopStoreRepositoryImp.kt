package com.bimm.takehomeassignmnent.data.repository

import com.bimm.takehomeassignmnent.data.dataSource.json.ShopStoreJsonSource
import com.bimm.takehomeassignmnent.data.dataSource.room.ShopStoreRoomDataSource
import com.bimm.takehomeassignmnent.data.dataSource.room.toDomainModel
import com.bimm.takehomeassignmnent.domain.models.ShopStore
import com.bimm.takehomeassignmnent.domain.models.toEntity
import com.bimm.takehomeassignmnent.domain.repository.ShopStoresRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class ShopStoreRepositoryImp(
  private val jsonService: ShopStoreJsonSource,
  private val roomDataBaseSource: ShopStoreRoomDataSource
): ShopStoresRepository{
  override fun getAll(): Flow<List<ShopStore>> {
    val roomShops = roomDataBaseSource.getAll().onEach{ shops ->
      if(shops.isEmpty()){
        val formatted = jsonService.getAll().map { it.toEntity() }
        roomDataBaseSource.save(formatted)
      }
    }
    return roomShops.map { shops -> shops.map { it.toDomainModel() } }
  }

  override fun findById(id: Int): Flow<ShopStore> {
    return roomDataBaseSource.getById(id).map { it.toDomainModel() }
  }

  override suspend fun save(shopStores: List<ShopStore>) {
    val shops = shopStores.map { it.toEntity() }
    roomDataBaseSource.save(shops)
  }
}