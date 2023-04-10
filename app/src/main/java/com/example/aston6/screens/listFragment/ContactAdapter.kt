package com.example.aston6.screens.listFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.aston6.databinding.ListItemBinding
import com.example.aston6.repository.ListItem

class ContactAdapter : ListAdapter<ListItem, ListViewHolder>(DiffUtilCallback()) {

//    var listItem: ArrayList<ListItem> = arrayListOf()
//        set(newValue) {
//            val diffUilCallback = DiffUtilCallback(field, newValue)
//            val difResult = DiffUtil.calculateDiff(diffUilCallback)
//            field = newValue
//            difResult.dispatchUpdatesTo(this)
//        }

    var onClickListener: ((ListItem) -> Unit)? = null
    var onLongClickListener: ((ListItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val itemPosition = getItem(position)
        holder.bind(itemPosition)

        holder.binding.root.setOnClickListener {
            onClickListener?.invoke(itemPosition)
        }
        holder.binding.root.setOnLongClickListener {
            onLongClickListener?.invoke(itemPosition)
            false
        }
    }
}








