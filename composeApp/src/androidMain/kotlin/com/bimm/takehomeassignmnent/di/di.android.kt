package com.bimm.takehomeassignmnent.di

import com.bimm.takehomeassignmnent.data.dataSource.json.JsonReader
import org.koin.dsl.module
import com.bimm.takehomeassignmnent.database.getBuilder
import com.bimm.takehomeassignmnent.utils.ExternalLink
import com.bimm.takehomeassignmnent.utils.ExternalLinkAndroid

actual val jsonReader = module {
    single<JsonReader> { com.bimm.takehomeassignmnent.utils.JsonReaderAndroid(get()) }
}
actual val database = module{
    single{ getBuilder(get()) }
}
actual val urlLink = module {
    single<ExternalLink> { ExternalLinkAndroid(get()) }
}