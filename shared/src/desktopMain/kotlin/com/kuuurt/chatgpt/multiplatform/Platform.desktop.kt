package com.kuuurt.chatgpt.multiplatform

import com.kuuurt.chatgpt.multiplatform.Platform

class DesktopPlatform : Platform {
  override val name: String = "Desktop"
}

actual fun getPlatform(): Platform = DesktopPlatform()