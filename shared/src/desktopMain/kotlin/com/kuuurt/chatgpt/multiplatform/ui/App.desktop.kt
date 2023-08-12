package com.kuuurt.chatgpt.multiplatform.ui

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.kuuurt.chatgpt.multiplatform.ui.ChatScreen

fun main() = application {
  Window(onCloseRequest = ::exitApplication) {
    ChatScreen()
  }
}