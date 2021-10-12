package com.example.dogsapp.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogsapp.data.remote.DogsApi
import com.example.dogsapp.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val api: DogsApi
) : ViewModel() {

    private val _state = mutableStateOf(DataState())
    val state: State<DataState> = _state

    init {
        getDog()
    }

    fun getDog() = viewModelScope.launch {
        try {
            _state.value = state.value.copy(isLoading = true)
            _state.value = state.value.copy(
                dog = api.getDog(),
                isLoading = false
            )
        } catch (e: Exception) {
            Log.e("DogViewModel", "getDog() ---> ", e)
            _state.value = state.value.copy(isLoading = false)
        }
    }


}