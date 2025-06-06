package com.bimm.takehomeassignmnent.data.dataSource.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopStoreDao {
  @Query("SELECT * FROM ShopStoreEntity")
  fun getAll():Flow<List<ShopStoreEntity>>

  @Query("SELECT * FROM ShopStoreEntity WHERE id = :id")
  fun findById(id: Int): Flow<ShopStoreEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun save(shopStores: List<ShopStoreEntity>)
}