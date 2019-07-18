package com.fasthub.pages

import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.fasthub.utils.findObjectById
import com.fasthub.utils.findObjectById2

class AuthPage(device: UiDevice) {
    val headerLaunch = device.findObjectById("com.fastaccess.github:id/mainCard")
    val btnAuthLaunch = device.findObjectById("com.fastaccess.github:id/basicAuth")
    val btnBrowserLaunch = device.findObjectById("com.fastaccess.github:id/browserLogin")

    val headerBasicAuth = device.findObjectById("com.fastaccess.github:id/mainCard")
    val headerUserName = device.findObjectById("com.fastaccess.github:id/username")
    val txtUserName = device.findObjectById("com.fastaccess.github:id/usernameEditText")
    val txtPassword = device.findObjectById("com.fastaccess.github:id/passwordEditText")
    val btnHidePassword = device.findObjectById("com.fastaccess.github:id/text_input_password_toggle")
    val btnLogin = device.findObjectById("com.fastaccess.github:id/login")

    val errUserName = headerUserName.getChild(UiSelector().className("android.widget.LinearLayout"))
        .getChild(UiSelector().resourceId("com.fastaccess.github:id/textinput_error"))
    val errPassword = device.findObjectById("com.fastaccess.github:id/password").getChild(UiSelector().className("android.widget.LinearLayout"))
        .getChild(UiSelector().resourceId("com.fastaccess.github:id/textinput_error"))

}