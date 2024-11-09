package com.example.messagingapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import com.example.messagingapp.service.WebSocketListeners
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(SavedStateHandleSaveableApi::class)
@HiltViewModel
class MainViewModel@Inject constructor(
    webSocketListener:WebSocketListeners
) : ViewModel() {
private val _socketStatus = MutableLiveData(false)
val socketStatus: LiveData<Boolean> = _socketStatus

private val _messages = MutableLiveData<Pair<Boolean, String>>()
val messages: LiveData<Pair<Boolean, String>> = _messages

fun addMessage(message: Pair<Boolean, String>) = viewModelScope.launch(Dispatchers.Main) {
    if (_socketStatus.value == true) {
        _messages.value = message
    }
}

fun setStatus(status: Boolean) = viewModelScope.launch(Dispatchers.Main) {
    _socketStatus.value = status
}
}