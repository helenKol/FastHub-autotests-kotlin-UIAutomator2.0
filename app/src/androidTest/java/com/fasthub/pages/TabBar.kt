package com.fasthub.pages

import androidx.test.uiautomator.UiDevice
import com.fasthub.utils.findObjectById
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat

class TabBar(device: UiDevice) {
    val panel = device.findObjectById("com.fastaccess.github:id/bottomNavigation")
    val btnFeed = device.findObjectById("com.fastaccess.github:id/feeds")
    val btnIssues = device.findObjectById("com.fastaccess.github:id/pinned")
    val btnPullRequests = device.findObjectById("com.fastaccess.github:id/pullRequests")

    fun check(){
        assertThat(true, CoreMatchers.`is`(panel.exists()))
        assertThat(true, CoreMatchers.`is`(btnFeed.exists()))
        assertThat(true, CoreMatchers.`is`(btnIssues.exists()))
        assertThat(true, CoreMatchers.`is`(btnPullRequests.exists()))
    }
}