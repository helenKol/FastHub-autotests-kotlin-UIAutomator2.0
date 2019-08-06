package com.fasthub.pages

import androidx.test.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.fasthub.utils.findObjectById
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*

class MenuPage {
    private var device: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    val tabMenu = device.findObject(UiSelector().className("android.support.v7.app.ActionBar\$Tab").index(0))
        .getChild(UiSelector().className("android.widget.TextView").text("MENU"))
    val tabProfile = device.findObject(UiSelector().className("android.support.v7.app.ActionBar\$Tab").index(1))
        .getChild(UiSelector().className("android.widget.TextView").text("PROFILE"))
    val btnHome = device.findObject(UiSelector().resourceId("com.fastaccess.github:id/design_menu_item_text").text("Home"))
    val btnProfile = device.findObject(UiSelector().resourceId("com.fastaccess.github:id/design_menu_item_text").text("Profile"))
    val btnOrganizations = device.findObject(UiSelector().resourceId("com.fastaccess.github:id/design_menu_item_text").text("Organizations"))
    val btnNotifications = device.findObject(UiSelector().resourceId("com.fastaccess.github:id/design_menu_item_text").text("Notifications"))
    val btnPinned = device.findObject(UiSelector().resourceId("com.fastaccess.github:id/design_menu_item_text").text("Pinned"))
    val btnTrending = device.findObject(UiSelector().resourceId("com.fastaccess.github:id/design_menu_item_text").text("Trending"))
    val btnGists = device.findObject(UiSelector().resourceId("com.fastaccess.github:id/design_menu_item_text").text("Gists"))
    val btnReport = device.findObject(UiSelector().resourceId("com.fastaccess.github:id/design_menu_item_text").text("Report an issue"))
    val btnFAQ = device.findObject(UiSelector().resourceId("com.fastaccess.github:id/design_menu_item_text").text("FAQ"))
    val btnSettings = device.findObjectById("com.fastaccess.github:id/feeds")

    val btnLogout = device.findObjectById("com.fastaccess.github:id/logout")
    val btnAddAccount = device.findObjectById("com.fastaccess.github:id/addAccLayout")
    val btnRepositories = device.findObjectById("com.fastaccess.github:id/repos")
    val btnStarted = device.findObjectById("com.fastaccess.github:id/starred")
    val btnProfilePinned = device.findObjectById("com.fastaccess.github:id/togglePinned")

    inner class toolBar{
        val imgAvatar = device.findObjectById("com.fastaccess.github:id/avatar")
        val txtFullName = device.findObjectById("com.fastaccess.github:id/navFullName")
        val txtUserName = device.findObjectById("com.fastaccess.github:id/navUsername")

        fun check(){
            assertThat(true, `is`(imgAvatar.exists()))
            assertThat(true, `is`(txtFullName.exists()))
            assertThat(true, `is`(txtUserName.exists()))
            assertThat(true, `is`(txtUserName.text.isNotEmpty()))
        }
    }

    fun check(){
        toolBar().check()

        assertThat(true, `is`(tabMenu.exists()))
        tabMenu.click()
        assertThat(true, `is`(tabMenu.isSelected))
        assertThat(true, `is`(btnHome.exists()))
        assertThat(true, `is`(btnProfile.exists()))
        assertThat(true, `is`(btnOrganizations.exists()))
        assertThat(true, `is`(btnNotifications.exists()))
        assertThat(true, `is`(btnPinned.exists()))
        assertThat(true, `is`(btnTrending.exists()))
        assertThat(true, `is`(btnGists.exists()))
        assertThat(true, `is`(btnReport.exists()))
        assertThat(true, `is`(btnFAQ.exists()))

        assertThat(true, `is`(tabProfile.exists()))
        tabProfile.click()
        assertThat(true, `is`(tabProfile.isSelected))
        assertThat(true, `is`(btnLogout.exists()))
        assertThat(true, `is`(btnAddAccount.exists()))
        assertThat(true, `is`(btnRepositories.exists()))
        assertThat(true, `is`(btnStarted.exists()))
        assertThat(true, `is`(btnProfilePinned.exists()))
    }
}