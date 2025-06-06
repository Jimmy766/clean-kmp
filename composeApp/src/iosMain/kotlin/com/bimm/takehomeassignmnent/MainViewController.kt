package com.bimm.takehomeassignmnent

import androidx.compose.ui.window.ComposeUIViewController
import com.bimm.takehomeassignmnent.di.initKoin

fun MainViewController() = ComposeUIViewController( configure = { initKoin() }) { App() }