package com.kuuurt.compose.multiplatform

class DesktopPlatform : Platform {
  override val name: String = "Desktop"
}

actual fun getPlatform(): Platform = DesktopPlatform()