package com.alfilippov.junetechsample.data

import okhttp3.Call

interface ApiService {

    suspend fun getUsers(call: Call): String
}