package com.kuuurt.chatgpt.multiplatform.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuuurt.chatgpt.multiplatform.ChatUIState

@Composable
fun ChatScreen() {
  AppTheme {
    val coroutineScope = rememberCoroutineScope()
    val state = remember { ChatUIState(coroutineScope) }

    Column(
      verticalArrangement = Arrangement.spacedBy(16.dp),
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
      Text("Multiplatform ChatGPT App")
      Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f)
          .verticalScroll(rememberScrollState())
      ) {
        Spacer(modifier = Modifier.weight(1f))
        state.messages.forEach {
          Message(it.content, it.role == "user")
        }
        if (state.isWaitingForResponse) {
          Message("...", false)
        }
      }
      TextField(
        value = state.message,
        onValueChange = { state.message = it },
        placeholder = { Text("Message") },
        trailingIcon = {
          Icon(
            Icons.Default.Send,
            contentDescription = null,
            tint = AppColor.CoolGray900,
            modifier = Modifier.clickable { state.sendMessage() }
          )
        },
        modifier = Modifier.fillMaxWidth()
      )
    }
  }
}

@Composable
fun ColumnScope.Message(
  message: String,
  isMyMessage: Boolean
) {
  Box(modifier = Modifier
    .fillMaxWidth(0.7f)
    .align(if (isMyMessage) Alignment.End else Alignment.Start)
  ) {
    Text(
      text = message,
      color = if (isMyMessage) AppColor.CoolGray200 else AppColor.CoolGray900,
      modifier = Modifier
        .background(
          color = if (isMyMessage) AppColor.CoolGray900 else AppColor.CoolGray200,
          shape = RoundedCornerShape(16.dp)
        )
        .align(if (isMyMessage) Alignment.CenterEnd else Alignment.CenterStart)
        .padding(16.dp)
    )
  }
}
