package com.fasthub.pages

import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.fasthub.utils.findObjectById
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat

class NavBar(device: UiDevice) {
    val panel = device.findObjectById("com.fastaccess.github:id/toolbar")
    val btnMenu = panel.getChild(UiSelector().className("android.widget.ImageButton"))
    val txtTitle = panel.getChild(UiSelector().className("android.widget.TextView").text("FastHub"))
    val btnNotify = device.findObjectById("com.fastaccess.github:id/notifications")
    val btnSearch = device.findObjectById("com.fastaccess.github:id/search")

    fun check() {
        assertThat(true, CoreMatchers.`is`(panel.exists()))
        assertThat(true, CoreMatchers.`is`(btnMenu.exists()))
        assertThat(true, CoreMatchers.`is`(txtTitle.exists()))
        assertThat(true, CoreMatchers.`is`(btnNotify.exists()))
        assertThat(true, CoreMatchers.`is`(btnSearch.exists()))
    }
}