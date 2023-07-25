package com.github.mikanichinose.backpress.system

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.github.mikanichinose.backpress.FirstFragment
import com.github.mikanichinose.backpress.R

class SystemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        if (savedInstanceState == null) {
            if (intent.getBooleanExtra(KEY_IS_ADD_TO_BACK_STACK, false)) {
                addFirstFragmentWithNullBackStack() // 空のFrameLayoutに戻れる
//            addFirstFragmentWithBackStack() // 空のFrameLayoutに戻れる
            } else {
                addFirstFragment()
            }
        }
    }

    private fun addFirstFragment() {
        supportFragmentManager.commit {
            add(R.id.container, FirstFragment.newInstance(), FirstFragment.TAG)
        }
    }

    private fun addFirstFragmentWithNullBackStack() {
        supportFragmentManager.commit {
            add(R.id.container, FirstFragment.newInstance(), FirstFragment.TAG)
            addToBackStack(null)
        }
    }

    private fun addFirstFragmentWithBackStack() {
        supportFragmentManager.commit {
            add(R.id.container, FirstFragment.newInstance(), FirstFragment.TAG)
            addToBackStack("FirstFragment")
        }
    }

    companion object {
        private const val KEY_IS_ADD_TO_BACK_STACK = "isAddToBackStack"
        fun createIntent(
            context: Context?,
            isAddToBackStack: Boolean
        ) = Intent(context, SystemActivity::class.java).apply {
            putExtra(KEY_IS_ADD_TO_BACK_STACK, isAddToBackStack)
        }
    }
}