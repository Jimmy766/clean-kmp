package com.bimm.takehomeassignmnent.di

import androidx.room.RoomDatabase
import com.bimm.takehomeassignmnent.data.dataSource.json.ShopStoreJsonSource
import com.bimm.takehomeassignmnent.data.dataSource.room.ShopStoreDao
import com.bimm.takehomeassignmnent.data.dataSource.room.ShopStoreDataBase
import com.bimm.takehomeassignmnent.data.dataSource.room.ShopStoreRoomDataSource
import com.bimm.takehomeassignmnent.data.repository.ShopStoreRepositoryImp
import com.bimm.takehomeassignmnent.domain.repository.ShopStoresRepository
import com.bimm.takehomeassignmnent.domain.usecase.ShopStoreUseCase
import com.bimm.takehomeassignmnent.ui.detail.DetailViewModel
import com.bimm.takehomeassignmnent.ui.home.HomeViewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val jsonReader: Module
expect val database : Module
expect val urlLink: Module

val dataModules = module {
  factoryOf(::ShopStoreJsonSource)
  single<ShopStoreDao> {
    val db = get<RoomDatabase.Builder<ShopStoreDataBase>>()
    db.build().shopStoreDao()
  }
  factoryOf(::ShopStoreRoomDataSource)
  factoryOf(::ShopStoreRepositoryImp) bind ShopStoresRepository::class
}

val useCasesModules = module {
  factoryOf(::ShopStoreUseCase)
}

val viewModelsModules = module {
  viewModelOf(::HomeViewModel)
  viewModelOf(::DetailViewModel)
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
      config?.invoke(this)
      modules(
        jsonReader,
        urlLink,
        database,
        dataModules,
        useCasesModules,
        viewModelsModules
      )
    }
}