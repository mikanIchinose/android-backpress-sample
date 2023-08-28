package com.github.mikanichinose.backpress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.github.mikanichinose.backpress.databinding.FragmentFirstBinding
import com.github.mikanichinose.backpress.onbackpresseddispatcher.OnBackPressedDispatcherActivity
import timber.log.Timber

class FirstFragment : Fragment(), BackPressFragment {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View {
//    if (requireActivity() is OnBackPressedDispatcherActivity) {
//      requireActivity().onBackPressedDispatcher.addCallback(this) {
//        Timber.d("back press!!")
//      }
//      requireActivity().onBackPressedDispatcher.addCallback {
//        Timber.d("back press!!")
//      }
//    }
    val binding: FragmentFirstBinding = FragmentFirstBinding.inflate(inflater)
    binding.button1.setOnClickListener { addSecondFragment() }
    binding.button1.isVisible = false
    binding.button2.setOnClickListener { addSecondFragmentWithNullBackStack() }
    binding.button3.setOnClickListener {
      startActivity(
        OnBackPressedDispatcherActivity.createIntent(requireContext(), false)
      )
    }
    return binding.root
  }

  override fun onBackPressed(): Boolean {
    Timber.d("onBackPressed")
    requireActivity().supportFragmentManager.popBackStack()
    return true
  }

  private fun addSecondFragment() {
    requireActivity().supportFragmentManager.commit {
      add(R.id.container, SecondFragment.newInstance())
    }
  }

  private fun addSecondFragmentWithNullBackStack() {
    requireActivity().supportFragmentManager.commit {
      add(R.id.container, SecondFragment.newInstance())
      addToBackStack(null)
    }
  }

  companion object {
    fun newInstance() = FirstFragment()
  }
}
