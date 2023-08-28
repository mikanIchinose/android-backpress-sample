package com.github.mikanichinose.backpress.extension

import androidx.fragment.app.FragmentActivity

inline fun <reified T> FragmentActivity.getTopFragment() =
  supportFragmentManager.fragments.lastOrNull()?.let { it as? T }
