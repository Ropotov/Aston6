package com.example.aston6.screens

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.aston6.R
import com.example.aston6.screens.listFragment.ListFragment

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, ListFragment.newInstance())
            .commit()
    }
}