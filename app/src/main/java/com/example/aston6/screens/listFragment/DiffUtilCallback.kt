package com.example.aston6.screens.listFragment

import androidx.recyclerview.widget.DiffUtil
import com.example.aston6.repository.ListItem

class DiffUtilCallback(
    private val oldList: MutableList<ListItem>,
    private val newList: MutableList<ListItem>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }
}