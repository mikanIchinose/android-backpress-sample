package com.github.mikanichinose.backpress

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.github.mikanichinose.backpress.databinding.FragmentSecondBinding
import timber.log.Timber

class SecondFragment : Fragment(), BackPressHandler {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        val TAG: String = SecondFragment::class.java.simpleName

        fun newInstance() = SecondFragment()
    }
}
