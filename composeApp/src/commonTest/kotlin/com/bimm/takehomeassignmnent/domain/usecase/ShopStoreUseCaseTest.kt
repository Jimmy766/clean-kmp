package com.bimm.takehomeassignmnent.domain.usecase

import com.bimm.takehomeassignmnent.domain.models.ShopStore
import com.bimm.takehomeassignmnent.domain.repository.FakeRepository
import com.bimm.takehomeassignmnent.domain.repository.ShopStoresRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ShopStoreUseCaseTest{

 private lateinit var repository: ShopStoresRepository
 private lateinit var useCase: ShopStoreUseCase

 @BeforeTest
 fun setup() {
  repository = FakeRepository()
  useCase = ShopStoreUseCase(repository)
 }

 @Test
 fun getAll() = runTest {
  val shopStores = listOf(
   ShopStore(
    1, "Shop 1", "Address 1", "Description 1",
    rating = 4.0,
    address = "somewhere",
    coordinates = listOf(121212333.0, 1212222.9),
    google_maps_link = "somewhere",
    website = "mywebsite.com"
   ),
   ShopStore(
    2, "Shop 2", "Address 2", "Description 2",
    rating = 5.0,
    address = "somewhere else",
    coordinates = listOf(121222.0, 12222.9),
    google_maps_link = "somewhere else",
    website = "mywebsite2.com"
   )
  )
  useCase.save(shopStores)
  val flow = useCase.getAll().first()
  assertEquals(flow.isNotEmpty(), true)
  assertEquals(flow.size, 2)
 }

 @Test
 fun findById() = runTest {
  val shopStore = ShopStore(
   1, "Shop 1", "Address 1", "Description 1",
   rating = 4.0,
   address = "somewhere",
   coordinates = listOf(121212333.0, 1212222.9),
   google_maps_link = "somewhere",
   website = "mywebsite.com"
  )
  useCase.save(listOf(shopStore))

  val flow = useCase.findById(1).first()
  assertEquals(flow.id, 1)
  assertEquals(flow.name, "Shop 1")
 }

}