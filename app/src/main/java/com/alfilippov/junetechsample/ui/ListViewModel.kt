package com.alfilippov.junetechsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.alfilippov.junetechsample.data.MainRepository
import com.alfilippov.junetechsample.dto.Resource
import kotlinx.coroutines.Dispatchers

class ListViewModel(private val mainRepository: MainRepository ) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}