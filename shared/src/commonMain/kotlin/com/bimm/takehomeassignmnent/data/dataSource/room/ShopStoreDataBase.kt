package com.bimm.takehomeassignmnent.data.dataSource.room

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities = [ShopStoreEntity::class], version = 1)
@ConstructedBy(ShopStoreDataBaseConstructor::class)
abstract class ShopStoreDataBase: RoomDatabase() {
  abstract fun shopStoreDao(): ShopStoreDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object ShopStoreDataBaseConstructor: RoomDatabaseConstructor<ShopStoreDataBase>{
  override fun initialize(): ShopStoreDataBase
}

fun getRoomDataBase(builder: RoomDatabase.Builder<ShopStoreDataBase>): ShopStoreDataBase {
  return builder
    .addMigrations()
    .fallbackToDestructiveMigrationOnDowngrade(true)
    .setDriver(BundledSQLiteDriver())
    .setQueryCoroutineContext(Dispatchers.IO)
    .build()
}