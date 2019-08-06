package com.fasthub.pages

import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.fasthub.utils.findObjectById
import com.fasthub.utils.findObjectById2
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat

class AuthPage(device: UiDevice) {
    val headerLaunch = device.findObjectById("com.fastaccess.github:id/mainCard")
    val btnAuthLaunch = device.findObjectById("com.fastaccess.github:id/basicAuth")
    val btnBrowserLaunch = device.findObjectById("com.fastaccess.github:id/browserLogin")

    val headerBasicAuth = device.findObjectById("com.fastaccess.github:id/mainCard")
    val btnBasicAuth = device.findObjectById("com.fastaccess.github:id/basicAuth")
    val headerUserName = device.findObjectById("com.fastaccess.github:id/username")
    val txtUserName = device.findObjectById("com.fastaccess.github:id/usernameEditText")
    val txtPassword = device.findObjectById("com.fastaccess.github:id/passwordEditText")
    val btnHidePassword = device.findObjectById("com.fastaccess.github:id/text_input_password_toggle")
    val btnLogin = device.findObjectById("com.fastaccess.github:id/login")
    val btnBrowserOpen = device.findObjectById("com.fastaccess.github:id/browserLogin")


    val errUserName = headerUserName.getChild(UiSelector().className("android.widget.LinearLayout"))
        .getChild(UiSelector().resourceId("com.fastaccess.github:id/textinput_error"))
    val errPassword = device.findObjectById("com.fastaccess.github:id/password")
        .getChild(UiSelector().className("android.widget.LinearLayout"))
        .getChild(UiSelector().resourceId("com.fastaccess.github:id/textinput_error"))

    fun check() {
        assertThat(true, `is`(headerBasicAuth.exists()))
        btnBasicAuth.clickAndWaitForNewWindow()
        assertThat(true, `is`(headerUserName.text == "Username"))
        assertThat(true, `is`(txtUserName.text.isEmpty()))
        assertThat(true, `is`(txtUserName.isFocused))
        assertThat(true, `is`(txtPassword.exists()))
        assertThat(true, `is`(btnHidePassword.exists()))
        assertThat(true, `is`(btnLogin.exists()))
        assertThat(true, `is`(btnBrowserOpen.exists()))
    }
}