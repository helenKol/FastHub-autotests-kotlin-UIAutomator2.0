package com.fasthub.pages

import androidx.test.uiautomator.UiDevice
import com.fasthub.utils.findObjectById

class TabBar(device: UiDevice) {
    val tabBar = device.findObjectById("com.fastaccess.github:id/bottomNavigation")
    val btnFeed = device.findObjectById("com.fastaccess.github:id/feeds")
    val btnIssues = device.findObjectById("com.fastaccess.github:id/pinned")
    val btnPullRequests = device.findObjectById("com.fastaccess.github:id/pullRequests")
}