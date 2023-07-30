package com.github.mikanichinose.backpress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.github.mikanichinose.backpress.databinding.FragmentFirstBinding

class FirstFragment : Fragment(), BackPressHandler {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding: FragmentFirstBinding = FragmentFirstBinding.inflate(inflater)
        binding.button1.setOnClickListener { addSecondFragment() }
        binding.button2.setOnClickListener { addSecondFragmentWithNullBackStack() }
        binding.button3.setOnClickListener { addSecondFragmentWithBackStack() }
        binding.button4.setOnClickListener { replaceSecondFragment() }
        binding.button5.setOnClickListener { replaceSecondFragmentWithNullBackStack() }
        return binding.root
    }

    override fun onBackPressed(): Boolean {
        addSecondFragmentWithBackStack()
        return true
    }

    private fun addSecondFragment() {
        requireActivity().supportFragmentManager.commit {
            add(R.id.container, SecondFragment.newInstance(), SecondFragment.TAG)
        }
    }

    private fun addSecondFragmentWithNullBackStack() {
        requireActivity().supportFragmentManager.commit {
            add(R.id.container, SecondFragment.newInstance(), SecondFragment.TAG)
            addToBackStack(null)
        }
    }

    private fun addSecondFragmentWithBackStack() {
        requireActivity().supportFragmentManager.commit {
            add(R.id.container, SecondFragment.newInstance(), SecondFragment.TAG)
            addToBackStack("SecondFragment")
        }
    }

    private fun replaceSecondFragment() {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.container, SecondFragment.newInstance(), SecondFragment.TAG)
        }
    }

    private fun replaceSecondFragmentWithNullBackStack() {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.container, SecondFragment.newInstance(), SecondFragment.TAG)
            addToBackStack(null)
        }
    }

    companion object {
        val TAG: String = FirstFragment::class.java.simpleName

        fun newInstance() = FirstFragment()
    }
}
