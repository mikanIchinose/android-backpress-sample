package com.github.mikanichinose.backpress.onbackpresseddispatcher

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.github.mikanichinose.backpress.FirstFragment
import com.github.mikanichinose.backpress.R

class OnBackPressedDispatcherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_back_pressed_dispatcher)
        if (savedInstanceState == null) {
            addFirstFragment() // 期待通り
//            addFirstFragmentToBackStack() // 空のFrameLayoutに戻れる
//            addFirstFragmentToBackStackWithName() // 空のFrameLayoutに戻れる
        }
        onBackPressedDispatcher.addCallback {
            if (supportFragmentManager.backStackEntryCount <= 1) {
                finish()
            } else {
                supportFragmentManager.popBackStack()
            }
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
        fun createIntent(
            context: Context?
        ) = Intent(context, OnBackPressedDispatcherActivity::class.java)
    }
}