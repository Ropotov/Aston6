package com.example.aston6.screens

import com.example.aston6.repository.ListItem

interface Navigator {
    fun navigateToDetailFragment(listItem: ListItem)
}