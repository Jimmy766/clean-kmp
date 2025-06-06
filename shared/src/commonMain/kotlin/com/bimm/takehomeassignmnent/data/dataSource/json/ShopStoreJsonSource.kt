package com.bimm.takehomeassignmnent.data.dataSource.json

import com.bimm.takehomeassignmnent.domain.models.ShopStore
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class ShopStoreJsonSource(
  private val jsonReader: JsonReader
) {
  private val jsonFormat = Json { ignoreUnknownKeys = true }

  suspend fun getAll(): List<ShopStore> {
    val shops = jsonReader.read("sakeshop.json")
    return jsonFormat.decodeFromString(ListSerializer(ShopStore.serializer()), shops)
  }
}