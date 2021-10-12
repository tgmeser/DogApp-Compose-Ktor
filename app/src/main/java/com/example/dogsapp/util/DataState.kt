package com.example.dogsapp.util

import com.example.dogsapp.data.model.Dog

data class DataState(
    val dog: Dog? = null,
    val isLoading: Boolean = false
)