package com.bimm.takehomeassignmnent.domain.usecase

import com.bimm.takehomeassignmnent.domain.models.ShopStore
import com.bimm.takehomeassignmnent.domain.repository.ShopStoresRepository

class ShopStoreUseCase(
    private val shopStoresRepository: ShopStoresRepository
) {

    suspend fun findById(id: Int) = shopStoresRepository.findById(id)

    suspend fun getAll() = shopStoresRepository.getAll()

    suspend fun save(shopStores: List<ShopStore>) =
        shopStoresRepository.save(shopStores)
}