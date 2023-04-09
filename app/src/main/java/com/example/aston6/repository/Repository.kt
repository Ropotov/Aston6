package com.example.aston6.repository

import kotlin.random.Random

class Repository {

    private var list = createListItem()

    private fun createListItem(): ArrayList<ListItem> {
        val list = arrayListOf<ListItem>()
        var id = 1
        while (list.size < 110) {
            list.add(
                ListItem(
                    id = id,
                    firstName = firstName[Random.nextInt(0, 11)],
                    lastName = lastName[Random.nextInt(0, 11)],
                    phone = randomPhone().toString(),
                    photo = "https://loremflickr.com/320/240/$id"
                )
            )
            id++
        }
        return list
    }

    fun getList(): ArrayList<ListItem> {
        return list
    }

    fun deleteItem(item: ListItem): ArrayList<ListItem> {
        list = ArrayList(list)
        val index = list.indexOf(item)
        list.removeAt(index)
        return list
    }

    fun editItem(item: ListItem): ArrayList<ListItem> {
        list = ArrayList(list)
        val index = list.indexOfFirst { it.id == item.id }
        list.removeAt(index)
        list.add(index, item)
        return list
    }

    private fun randomPhone(): Long {
        return (70000000000..79999999999).random()
    }

    companion object {

        private val firstName = listOf(
            "Nick",
            "Pavel",
            "Lori",
            "Petr",
            "Maksim",
            "Egor",
            "Gogi",
            "Mark",
            "Dima",
            "Alex",
            "Victor",
            "Ivan"
        )
        private val lastName = listOf(
            "Ivanov",
            "Egorov",
            "Lorionov",
            "Petrov",
            "Maksimov",
            "Tutov",
            "Goginov",
            "Markov",
            "Dimanidze",
            "Alexandrov",
            "Victorovich",
            "Sortov"
        )
    }
}

