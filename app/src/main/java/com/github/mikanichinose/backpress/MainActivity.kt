package com.github.mikanichinose.backpress

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikanichinose.backpress.databinding.ActivityMainBinding
import com.github.mikanichinose.backpress.onbackpressed.OverrideOnBackPressedActivity
import com.github.mikanichinose.backpress.system.SystemActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        binding.button1.setOnClickListener {
            val intent = SystemActivity.createIntent(this, false)
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            val intent = SystemActivity.createIntent(this, true)
            startActivity(intent)
        }
        binding.button3.setOnClickListener {
            val intent = OverrideOnBackPressedActivity.createIntent(this, false)
            startActivity(intent)
        }
        binding.button4.setOnClickListener {
            val intent = OverrideOnBackPressedActivity.createIntent(this, true)
            startActivity(intent)
        }
        setContentView(binding.root)
    }
}
