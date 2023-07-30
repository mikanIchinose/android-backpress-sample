package com.github.mikanichinose.backpress

import android.app.Application
import com.chesire.lifecyklelog.LifecykleLog
import timber.log.Timber

class App : Application() {
  override fun onCreate() {
    super.onCreate()
    Timber.plant(Timber.DebugTree())
    LifecykleLog.apply {
      initialize(this@App)
      requireAnnotation = false
    }
  }
}
