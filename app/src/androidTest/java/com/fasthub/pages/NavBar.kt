package com.fasthub.pages

import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.fasthub.utils.findObjectById

class NavBar(device: UiDevice) {
    val navBar = device.findObjectById("com.fastaccess.github:id/toolbar")
    val btnMenu = navBar.getChild(UiSelector().className("android.widget.ImageButton"))
    val txtTitle = navBar.getChild(UiSelector().className("android.widget.TextView").text("FastHub"))
    val btnNotify = device.findObjectById("com.fastaccess.github:id/notifications")
    val btnSearch = device.findObjectById("com.fastaccess.github:id/search")
}