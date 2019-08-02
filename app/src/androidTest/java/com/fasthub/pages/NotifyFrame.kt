package com.fasthub.pages

import androidx.test.uiautomator.UiDevice
import com.fasthub.utils.findObjectById

class NotifyFrame(device: UiDevice) {
    val notifyField = device.findObjectById("com.fastaccess.github:id/messageLayout")
    val notifyTitle = device.findObjectById("com.fastaccess.github:id/title")
    val btnCancel = device.findObjectById("com.fastaccess.github:id/cancel")
    val btnSubmit = device.findObjectById("com.fastaccess.github:id/ok")
}