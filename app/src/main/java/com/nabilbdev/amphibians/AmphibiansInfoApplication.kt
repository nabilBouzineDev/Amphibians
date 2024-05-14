package com.nabilbdev.amphibians

import android.app.Application
import com.nabilbdev.amphibians.data.AppContainer
import com.nabilbdev.amphibians.data.DefaultAppContainer

class AmphibiansInfoApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}