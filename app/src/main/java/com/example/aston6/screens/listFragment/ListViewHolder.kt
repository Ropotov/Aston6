package com.example.aston6.screens.listFragment

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.aston6.R
import com.example.aston6.databinding.ListItemBinding
import com.example.aston6.repository.ListItem

class ListViewHolder(val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(position: ListItem) {
        with(binding) {
            phone.text = position.phone
            binding.name.text = "${position.firstName}  ${position.lastName}"
            Glide
                .with(photo)
                .load(position.photo)
                .placeholder(R.drawable.ic_android_black_24dp)
                .centerCrop()
                .transform(RoundedCorners(24))
                .into(photo)
        }
    }
}
