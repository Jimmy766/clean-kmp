package com.bimm.takehomeassignmnent.data.dataSource.room

import kotlinx.coroutines.flow.Flow

class ShopStoreRoomDataSource(
  private val dao: ShopStoreDao
) {
  fun getAll(): Flow<List<ShopStoreEntity>> {
    return dao.getAll()
  }

  fun getById(id: Int): Flow<ShopStoreEntity> {
    return dao.findById(id)
  }

  suspend fun save(shopStores: List<ShopStoreEntity>) {
    dao.save(shopStores)
  }
}