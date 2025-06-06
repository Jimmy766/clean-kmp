package com.bimm.takehomeassignmnent.utils

import com.bimm.takehomeassignmnent.data.dataSource.json.JsonReader
import kotlinx.cinterop.BetaInteropApi
import platform.Foundation.NSBundle
import platform.Foundation.NSData
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.Foundation.dataWithContentsOfFile

class JsonReaderIOS: JsonReader {
    @OptIn(BetaInteropApi::class)
    override suspend fun read(file: String): String {
        val path = NSBundle.mainBundle.pathForResource(file.removeSuffix(".json"), "json")
            ?: throw IllegalArgumentException("File $file not found in main bundle")
        val content = NSData.dataWithContentsOfFile(path)
            ?: throw IllegalArgumentException("Could not read file $file")
        return NSString.create(content, NSUTF8StringEncoding) as String
    }
}