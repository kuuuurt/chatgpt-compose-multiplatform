package com.kuuurt.compose.multiplatform

interface Platform {
  val name: String
}

expect fun getPlatform(): Platform