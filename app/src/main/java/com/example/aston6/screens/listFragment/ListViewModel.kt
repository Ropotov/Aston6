package com.example.aston6.screens.listFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aston6.App
import com.example.aston6.repository.ListItem

class ListViewModel : ViewModel() {

    private val repository = App.REPOSITORY

    val list: MutableLiveData<ArrayList<ListItem>> = MutableLiveData()

    init {
        getList()
    }

    private fun getList() {
        list.value = repository.getList()
    }

    fun deleteItem(item: ListItem) {
        list.value = repository.deleteItem(item)
    }

    fun editItem(item: ListItem) {
        list.value = repository.editItem(item)
    }
}