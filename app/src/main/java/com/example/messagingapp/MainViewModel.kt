package com.example.messagingapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import com.example.messagingapp.service.WebSocketListeners
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class empoyeeState(
    val text:String="",
)
@OptIn(SavedStateHandleSaveableApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {
     val _webSocketData = MutableStateFlow<WebSocketData?>(null)
    val webSocketData: StateFlow<WebSocketData?> = _webSocketData

    var state by mutableStateOf(empoyeeState())

    fun ChangeName(text: String) {
        state = state.copy(
            text = text
        )
    }

}
data class WebSocketData(val message: String)