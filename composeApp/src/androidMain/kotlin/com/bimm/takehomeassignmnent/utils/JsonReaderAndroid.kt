package com.bimm.takehomeassignmnent.utils

import android.content.Context
import com.bimm.takehomeassignmnent.data.dataSource.json.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JsonReaderAndroid(private val context: Context): JsonReader {
  override suspend fun read(file: String): String = withContext(Dispatchers.IO) {
    context.assets.open(file).bufferedReader().use { it.readText() }
  }
}