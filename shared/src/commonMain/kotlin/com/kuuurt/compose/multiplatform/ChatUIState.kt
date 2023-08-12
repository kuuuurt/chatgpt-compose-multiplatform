package com.kuuurt.compose.multiplatform

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ChatUIState(val coroutineScope: CoroutineScope) {
  val chatGPTClient =  ChatGPTClient()
  val messages by mutableStateOf<MutableList<Message>>(mutableListOf())
  var message by mutableStateOf("")
  var isWaitingForResponse by mutableStateOf(false)

  fun sendMessage() {
    messages.add(Message("user", message))
    message = ""
    coroutineScope.launch(CoroutineExceptionHandler { _, throwable ->
      messages.add(Message("system", "Oops! We've failed to reach ChatGPT. Please try again."))
    }) {
      isWaitingForResponse = true
      val chatCompletion = chatGPTClient.createChatCompletion(messages)
      messages.add(chatCompletion.message)
      isWaitingForResponse = false
    }
  }
}