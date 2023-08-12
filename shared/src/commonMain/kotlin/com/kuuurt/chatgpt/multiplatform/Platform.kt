package com.kuuurt.chatgpt.multiplatform

interface Platform {
  val name: String
}

expect fun getPlatform(): Platform