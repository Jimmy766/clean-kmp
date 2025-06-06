package com.bimm.takehomeassignmnent.utils

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

class ExternalLinkIOS : ExternalLink {
  override fun openLink(url: String) {
    val uri = NSURL.URLWithString(url)
    if(uri != null){
      UIApplication.sharedApplication.openURL(
        url = uri,
        options = emptyMap<Any?, Any>(),
        completionHandler = null
      )
    }
  }
}