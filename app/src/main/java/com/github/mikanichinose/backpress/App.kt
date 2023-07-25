package com.github.mikanichinose.backpress

import android.app.Application
import com.chesire.lifecyklelog.LifecykleLog

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        LifecykleLog.apply {
            initialize(this@App)
            requireAnnotation = false
        }
    }
}