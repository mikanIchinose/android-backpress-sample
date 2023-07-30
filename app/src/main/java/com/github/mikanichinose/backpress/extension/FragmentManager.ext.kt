package com.github.mikanichinose.backpress.extension

import androidx.fragment.app.FragmentActivity

inline fun <reified T> FragmentActivity.getTopFragment() =
  supportFragmentManager.fragments.firstOrNull()?.let { it as? T }
