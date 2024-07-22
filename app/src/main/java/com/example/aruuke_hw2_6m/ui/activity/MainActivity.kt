package com.example.aruuke_hw2_6m.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aruuke_hw2_6m.R
import com.example.aruuke_hw2_6m.ui.fragment.cartoon.CartoonFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, CartoonFragment())
                .commit()
        }
    }
}