package com.alfilippov.junetechsample.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiHelper @Inject constructor( private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getUsers()
}