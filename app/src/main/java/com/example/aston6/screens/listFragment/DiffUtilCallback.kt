package com.example.aston6.screens.listFragment

import androidx.recyclerview.widget.DiffUtil
import com.example.aston6.repository.ListItem

class DiffUtilCallback : DiffUtil.ItemCallback<ListItem>() {

    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return false
    }
}