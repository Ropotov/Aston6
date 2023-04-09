package com.example.aston6.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListItem(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val photo: String
) : Parcelable