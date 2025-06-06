package com.bimm.takehomeassignmnent.data.dataSource.room

import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShopStoreRoomDataSourceTest {

  private lateinit var dao: ShopStoreDao
  private lateinit var database: ShopStoreDataBase
  private lateinit var dataSource: ShopStoreRoomDataSource

  val dispatcher = StandardTestDispatcher()

  @OptIn(ExperimentalCoroutinesApi::class)
  @Before
  fun setUp() {
  Dispatchers.setMain(dispatcher)
  database = Room.inMemoryDatabaseBuilder(
    ApplicationProvider.getApplicationContext(),
    ShopStoreDataBase::class.java
  ).allowMainThreadQueries().build()

  dao = database.shopStoreDao()
  dataSource = ShopStoreRoomDataSource(dao)
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  @After
  fun tearDown() {
   database.close()
   Dispatchers.resetMain()
  }

  @Test
  fun testGetAll() = runTest {
   val shopStores = listOf(
     ShopStoreEntity(
       1, "Shop 1", "Address 1", "Description 1",
       rating = 4.0,
       address = "somewhere",
       longitude = 12121212.0,
       latitude = 666666.0,
       googleMapsLink = "somewhere",
       website = "mywebsite.com"
     ),
     ShopStoreEntity(
       2, "Shop 2", "Address 2", "Description 2",
       rating = 5.0,
       address = "somewhere else",
       longitude = 12121212.0,
       latitude = 666666.0,
       googleMapsLink = "somewhere else",
       website = "mywebsite2.com"
     )
   )

   dataSource.save(shopStores)
   val dataSaved = dataSource.getAll().first()
   assert(dataSaved.isNotEmpty())
   assert(dataSaved.size == 2)
   assert(dataSaved[0].id == 1)
   assert(dataSaved[1].name == "Shop 2")
  }
  @Test
  fun testFindById() = runTest {
    val shopStores = listOf(
      ShopStoreEntity(
        1, "Shop 1", "Address 1", "Description 1",
        rating = 4.0,
        address = "somewhere",
        longitude = 12121212.0,
        latitude = 666666.0,
        googleMapsLink = "somewhere",
        website = "mywebsite.com"
      ),
      ShopStoreEntity(
        2, "Shop 2", "Address 2", "Description 2",
        rating = 5.0,
        address = "somewhere else",
        longitude = 12121212.0,
        latitude = 666666.0,
        googleMapsLink = "somewhere else",
        website = "mywebsite2.com"
      )
    )
    dataSource.save(shopStores)
    val dataSaved = dataSource.getById(1).first()
    assert(dataSaved.id == 1)
    assert(dataSaved.name == "Shop 1")
  }

}