package com.bimm.takehomeassignmnent.domain.models

import com.bimm.takehomeassignmnent.data.dataSource.room.ShopStoreEntity
import kotlinx.serialization.Serializable


@Serializable
data class ShopStore(
    val id: Int? = null,
    val name: String,
    val description: String,
    val picture: String?,
    val rating: Double,
    val address: String,
    val coordinates: List<Double>,
    val google_maps_link: String,
    val website: String
)

fun ShopStore.toEntity(): ShopStoreEntity {
    return ShopStoreEntity(
        name = name,
        description = description,
        picture = picture,
        rating = rating,
        address = address,
        longitude = coordinates[0],
        latitude = coordinates[1],
        googleMapsLink = google_maps_link,
        website = website
    )
}