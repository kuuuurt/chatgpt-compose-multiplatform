package com.kuuurt.chatgpt.multiplatform

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.accept
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class ChatGPTClient {
  val httpClient = HttpClient {
    install(ContentNegotiation) {
      json(Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
      })
    }
    install(Auth) {
      bearer {
        loadTokens {
          BearerTokens("<INSERT_CHATGPT_API_KEY>", "")
        }
      }
    }
  }

  suspend fun createChatCompletion(
    messages: List<Message>
  ): ChatCompletionChoice {
    val response = httpClient.post("https://api.openai.com/v1/chat/completions") {
      contentType(ContentType.Application.Json)
      accept(ContentType.Application.Json)
      setBody(CreateChatCompletion(messages, "gpt-3.5-turbo"))
    }
    return response.body<ChatCompletion>().choices[0]
  }
}

@Serializable
data class CreateChatCompletion(
  val messages: List<Message>,
  val model: String
)

@Serializable
data class ChatCompletion(
  val choices: List<ChatCompletionChoice>
)

@Serializable
data class ChatCompletionChoice(
  val message: Message
)