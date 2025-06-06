package com.bimm.takehomeassignmnent.database
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bimm.takehomeassignmnent.data.dataSource.room.ShopStoreDataBase


fun getBuilder(context: Context): RoomDatabase.Builder<ShopStoreDataBase> {
  val appContext = context.applicationContext
  val db = appContext.getDatabasePath("shops.db")
  return Room.databaseBuilder(
    context = appContext,
    name = db.absolutePath
  )
}