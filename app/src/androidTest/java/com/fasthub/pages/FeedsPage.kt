package com.fasthub.pages

import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.fasthub.utils.findObjectById

class FeedsPage(device: UiDevice) {
    val toolbar = device.findObjectById("com.fastaccess.github:id/toolbar")
    val btnMenu = toolbar.getChild(UiSelector().className("android.widget.ImageButton"))
    val header = toolbar.getChild(UiSelector().className("android.widget.TextView"))
    val btnNotify = device.findObjectById("com.fastaccess.github:id/notifications")
    val btnSearch = device.findObjectById("com.fastaccess.github:id/search")

    val notifyField = device.findObjectById("com.fastaccess.github:id/messageLayout")
    val notifyTitle = device.findObjectById("com.fastaccess.github:id/title")
    val btnNotifyCancel = device.findObjectById("com.fastaccess.github:id/cancel")
    val btnNotifySubmit = device.findObjectById("com.fastaccess.github:id/ok")
}