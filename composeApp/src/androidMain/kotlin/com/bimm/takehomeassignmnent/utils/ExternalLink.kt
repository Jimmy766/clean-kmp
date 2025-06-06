package com.bimm.takehomeassignmnent.utils

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

class ExternalLinkAndroid(private val context: Context): ExternalLink {
  override fun openLink(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
    val appContext = context.applicationContext
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    appContext.startActivity(intent)
  }
}