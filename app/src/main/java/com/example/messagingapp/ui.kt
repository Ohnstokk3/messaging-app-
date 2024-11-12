package com.example.messagingapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.messagingapp.nav.NavigationDestination
import com.example.messagingapp.service.WebSocketListeners
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket



object AnalyticsDestination : NavigationDestination {
    override val route = "Analytics"

}

private fun createRequest(): Request {
    val websocketURL = "ws://192.168.0.103:8080//ws"
    return Request.Builder()
        .url(websocketURL)
        .build()
}
private val okHttpClient = OkHttpClient()
private var webSocket: WebSocket? = null
@Composable
fun chat(webSocketListeners:WebSocketListeners,viewModels: MainViewModel= hiltViewModel()){
    val coroutineScope = rememberCoroutineScope()
    val webSocketData by viewModels.webSocketData.collectAsState()
    Column(modifier = Modifier.padding(top = 200.dp)) {
        Text(text = webSocketData?.message ?: "No data received")
        Spacer(modifier = Modifier.size(7.dp))
        Text(text = "${viewModels.state.text}")
    }
Row(modifier = Modifier
    .fillMaxWidth()
    .fillMaxSize()
    .padding(bottom = 70.dp, start = 10.dp),
    verticalAlignment = Alignment.Bottom
) {

    TextField(
        value = viewModels.state.text,
        onValueChange = { viewModels.ChangeName(it) },
        label = { Text("Label") }
    )
    Spacer(modifier = Modifier.size(7.dp))
    Button(onClick = {
        webSocket = okHttpClient.newWebSocket(createRequest(), webSocketListeners)
        webSocket?.send("${viewModels.state.text} ")
    }) {
        Icon(
            Icons.Default.Send,
            contentDescription=""
        )
    }
}

}