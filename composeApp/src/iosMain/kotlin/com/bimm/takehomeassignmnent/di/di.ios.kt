package com.bimm.takehomeassignmnent.di

import com.bimm.takehomeassignmnent.data.dataSource.json.JsonReader
import com.bimm.takehomeassignmnent.utils.JsonReaderIOS
import org.koin.dsl.module
import com.bimm.takehomeassignmnent.database.getDatabaseBuilder
import com.bimm.takehomeassignmnent.utils.ExternalLink
import com.bimm.takehomeassignmnent.utils.ExternalLinkIOS

actual val jsonReader = module{
  single<JsonReader> {
    JsonReaderIOS()
  }
}
actual val database = module{
  single{ getDatabaseBuilder() }
}
actual val urlLink = module {
  single<ExternalLink> { ExternalLinkIOS() }
}