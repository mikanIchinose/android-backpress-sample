package com.github.mikanichinose.backpress.onbackpressed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.github.mikanichinose.backpress.FirstFragment
import com.github.mikanichinose.backpress.R
import com.github.mikanichinose.backpress.SecondFragment
import com.github.mikanichinose.backpress.extension.getTopFragment
import timber.log.Timber

class OverrideOnBackPressedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_override_on_back_pressed)
        if (savedInstanceState == null) {
            if (intent.getBooleanExtra(KEY_IS_ADD_TO_BACK_STACK, false)) {
                addFirstFragmentToBackStack() // 期待通り
            } else {
                addFirstFragment() // BackStackを無視してActivityが終了する
            }
        }
    }

    override fun onBackPressed() {
        val isFirstFragmentConsumeBackPressed =
            getTopFragment<FirstFragment>()?.onBackPressed() == true
        if (isFirstFragmentConsumeBackPressed) {
            Timber.d("onBackPressed from FirstFragment")
            return
        }
        val isSecondFragmentConsumeBackPressed =
            getTopFragment<SecondFragment>()?.onBackPressed() == true
        if (isSecondFragmentConsumeBackPressed) {
            Timber.d("onBackPressed from SecondFragment")
            return
        }

        Timber.d("onBackPressed from OverrideOnBackPressedActivity")
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    private fun addFirstFragment() {
        supportFragmentManager.commit {
            add(R.id.container, FirstFragment.newInstance())
        }
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
            Intent(context, OverrideOnBackPressedActivity::class.java).apply {
                putExtra(KEY_IS_ADD_TO_BACK_STACK, isAddToBackStack)
            }
    }
}
