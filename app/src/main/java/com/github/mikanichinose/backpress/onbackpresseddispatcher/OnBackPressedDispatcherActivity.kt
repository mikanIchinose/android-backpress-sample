package com.github.mikanichinose.backpress.onbackpresseddispatcher

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import com.github.mikanichinose.backpress.FirstFragment
import com.github.mikanichinose.backpress.R
import timber.log.Timber

class OnBackPressedDispatcherActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_on_back_pressed_dispatcher)
    if (savedInstanceState == null) {
      if (intent.getBooleanExtra(KEY_IS_ADD_TO_BACK_STACK, false)) {
        addFirstFragmentToBackStack() // 空のFrameLayoutに戻れる
      } else {
        addFirstFragment() // 期待通り
      }
    }
    onBackPressedDispatcher.addCallback {
      Timber.d("back pressed!!")
//      remove()
      finish()
    }
  }

  private fun addFirstFragment() {
    supportFragmentManager.commit { add(R.id.container, FirstFragment.newInstance()) }
  }

  private fun addFirstFragmentToBackStack() {
    supportFragmentManager.commit {
      add(R.id.container, FirstFragment.newInstance())
      addToBackStack(null)
    }
  }

  companion object {
    private const val KEY_IS_ADD_TO_BACK_STACK = "isAddToBackStack"

    fun createIntent(
      context: Context?,
      isAddToBackStack: Boolean,
    ) =
      Intent(context, OnBackPressedDispatcherActivity::class.java).apply {
        bundleOf(
          KEY_IS_ADD_TO_BACK_STACK to isAddToBackStack
        )
      }
  }
}
