package com.example.aston6

import android.app.Application
import com.example.aston6.repository.Repository

class App : Application() {
    companion object {
        val REPOSITORY = Repository()
    }
}