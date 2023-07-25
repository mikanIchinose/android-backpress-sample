package com.github.mikanichinose.backpress.onbackpressed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.github.mikanichinose.backpress.FirstFragment
import com.github.mikanichinose.backpress.R

class OverrideOnBackPressedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_override_on_back_pressed)
        if (savedInstanceState == null) {
            if (intent.getBooleanExtra(KEY_IS_ADD_TO_BACK_STACK, false)) {
                addFirstFragmentToBackStack() // 期待通り
//            addFirstFragmentToBackStackWithName() // 期待通り
            } else {
                addFirstFragment() // BackStackを無視してActivityが終了する
            }
        }
    }

    override fun onBackPressed() {
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

    private fun addFirstFragmentToBackStackWithName() {
        supportFragmentManager.commit {
            add(R.id.container, FirstFragment.newInstance())
            addToBackStack("FirstFragment")
        }
    }

    companion object {
        private const val KEY_IS_ADD_TO_BACK_STACK = "isAddToBackStack"
        fun createIntent(
            context: Context?,
            isAddToBackStack: Boolean
        ) = Intent(context, OverrideOnBackPressedActivity::class.java).apply {
            putExtra(KEY_IS_ADD_TO_BACK_STACK, isAddToBackStack)
        }
    }
}