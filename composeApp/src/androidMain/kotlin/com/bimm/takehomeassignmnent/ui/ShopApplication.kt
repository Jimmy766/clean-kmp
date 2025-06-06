package com.bimm.takehomeassignmnent.ui

import android.app.Application
import com.bimm.takehomeassignmnent.di.initKoin
import org.koin.core.logger.Level
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger


class ShopApplication: Application() {

  override fun onCreate() {
    super.onCreate()
    initKoin{
      androidLogger(Level.DEBUG)
      androidContext(this@ShopApplication)
    }
  }
}