package com.example.tvshowaid

import android.app.Application

class App:Application() {

    private lateinit var appInstance : App

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    fun getInstance():App{
        return appInstance
    }
}