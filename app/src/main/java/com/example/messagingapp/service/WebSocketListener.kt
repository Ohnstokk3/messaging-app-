package com.example.messagingapp.service

import android.util.Log
import com.example.messagingapp.MainViewModel
import com.example.messagingapp.WebSocketData
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject


class WebSocketListeners  @Inject constructor(
    private val viewModel: MainViewModel
): WebSocketListener() {

    private val TAG = "Test"

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)

        webSocket.send("Android Device Connected")
        Log.d(TAG, "onOpen:")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        viewModel._webSocketData.value = WebSocketData(text)
        Log.d(TAG, "onMessage: $text")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        Log.d(TAG, "onClosing: $code $reason")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)

        Log.d(TAG, "onClosed: $code $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        Log.d(TAG, "onFailure: ${t.message} $response")
        super.onFailure(webSocket, t, response)
    }
}