package com.kuuurt.chatgpt.multiplatform

import com.kuuurt.chatgpt.multiplatform.Platform

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()