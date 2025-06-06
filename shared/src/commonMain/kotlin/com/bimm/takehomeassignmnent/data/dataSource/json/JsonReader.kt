package com.bimm.takehomeassignmnent.data.dataSource.json

interface JsonReader {
  suspend fun read(fileName: String): String
}