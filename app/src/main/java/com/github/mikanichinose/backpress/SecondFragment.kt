package com.github.mikanichinose.backpress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.github.mikanichinose.backpress.databinding.FragmentSecondBinding
import com.github.mikanichinose.backpress.onbackpresseddispatcher.OnBackPressedDispatcherActivity
import timber.log.Timber

class SecondFragment : Fragment(), BackPressFragment {
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    if (requireActivity() is OnBackPressedDispatcherActivity) {
      requireActivity().onBackPressedDispatcher.addCallback(this) {
        Timber.d("back press!! a")
        isEnabled = false
      }
      requireActivity().onBackPressedDispatcher.addCallback(this) {
        Timber.d("back press!! b")
        isEnabled = false
      }
      requireActivity().onBackPressedDispatcher.addCallback(this) {
        Timber.d("back press!! c")
        isEnabled = false
      }
//      requireActivity().onBackPressedDispatcher.addCallback {
//        Timber.d("back press!!")
//        requireActivity().supportFragmentManager.popBackStack()
//      }
    }
    val binding = FragmentSecondBinding.inflate(inflater)
    binding.button1.setOnClickListener { addFragment() }
    binding.button2.setOnClickListener { addFragmentWithNullBackStack() }
    return binding.root
  }

  // old fashion
  override fun onBackPressed(): Boolean {
    Timber.d("onBackPressed")
    requireActivity().supportFragmentManager.popBackStack()
    return true
  }

  private fun addFragment() {
    requireActivity().supportFragmentManager.commit {
      add(R.id.container, ThirdFragment.newInstance())
    }
  }

  private fun addFragmentWithNullBackStack() {
    requireActivity().supportFragmentManager.commit {
      add(R.id.container, ThirdFragment.newInstance())
      addToBackStack(null)
    }
  }

  companion object {
    fun newInstance() = SecondFragment()
  }
}
