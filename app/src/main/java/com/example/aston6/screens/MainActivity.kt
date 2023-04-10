package com.example.aston6.screens

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.aston6.R
import com.example.aston6.repository.ListItem
import com.example.aston6.screens.detailFragment.DetailFragment
import com.example.aston6.screens.listFragment.ListFragment

class MainActivity : FragmentActivity(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(R.id.container, ListFragment.newInstance(), false)
    }

    override fun navigateToDetailFragment(listItem: ListItem) {

        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                replaceFragment(R.id.container, DetailFragment.newInstance(listItem), true)
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                replaceFragment(R.id.containerDetail, DetailFragment.newInstance(listItem), true)
            }
        }
    }

    private fun replaceFragment(idContainer: Int, fragment: Fragment, addBackStack: Boolean) {
        if (addBackStack) {
            supportFragmentManager
                .beginTransaction()
                .replace(idContainer, fragment)
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(idContainer, fragment)
                .commit()
        }
    }
}