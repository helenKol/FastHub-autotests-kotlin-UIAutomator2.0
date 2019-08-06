package com.fasthub.pages

import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.fasthub.utils.findObjectById
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert

class FeedsPage(device: UiDevice) {
    val imgAvatar = device.findObjectById("com.fastaccess.github:id/avatar")
    val txtRepository = device.findObjectById("com.fastaccess.github:id/title")
    val txtDate = device.findObjectById("com.fastaccess.github:id/date")

    val btnReload = device.findObjectById("com.fastaccess.github:id/reload")

    fun check(){
        MatcherAssert.assertThat(true, CoreMatchers.`is`(imgAvatar.exists()))
        MatcherAssert.assertThat(true, CoreMatchers.`is`(txtRepository.exists()))
        MatcherAssert.assertThat(true, CoreMatchers.`is`(txtRepository.text.isNotEmpty()))
        MatcherAssert.assertThat(true, CoreMatchers.`is`(txtDate.exists()))
        MatcherAssert.assertThat(true, CoreMatchers.`is`(txtDate.text.isNotEmpty()))
    }
}