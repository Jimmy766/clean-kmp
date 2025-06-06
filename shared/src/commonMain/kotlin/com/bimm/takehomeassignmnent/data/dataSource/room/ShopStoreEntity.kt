package com.bimm.takehomeassignmnent.data.dataSource.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bimm.takehomeassignmnent.domain.models.ShopStore

@Entity
data class ShopStoreEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int? = null,
  val name: String,
  val description: String,
  val picture: String?,
  val rating: Double,
  val address: String,
  val longitude: Double,
  val latitude: Double,
  val googleMapsLink: String,
  val website: String
)

fun ShopStoreEntity.toDomainModel() = ShopStore(
  id= id,
  name = name,
  description = description,
  picture = picture,
  rating = rating,
  address = address,
  coordinates = listOf(longitude, latitude),
  google_maps_link = googleMapsLink,
  website = website
)