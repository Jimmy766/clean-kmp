package com.bimm.takehomeassignmnent.database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.bimm.takehomeassignmnent.data.dataSource.room.ShopStoreDataBase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask


fun getDatabaseBuilder(): RoomDatabase.Builder<ShopStoreDataBase> {
  val dbPath = documentDirectory() + "/shops.db"
  return Room.databaseBuilder<ShopStoreDataBase>(
    name = dbPath
  ).setDriver(BundledSQLiteDriver())
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
  val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
    directory = NSDocumentDirectory,
    inDomain = NSUserDomainMask,
    appropriateForURL = null,
    create = false,
    error = null,
  )

  return requireNotNull(documentDirectory?.path)
}