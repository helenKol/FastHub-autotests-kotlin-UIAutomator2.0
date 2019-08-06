package com.fasthub.tests

import android.content.Context
import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.fasthub.pages.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.UiObjectNotFoundException

const val LAUNCH_TIMEOUT: Long = 1_000L
const val BASIC_SAMPLE_PACKAGE = "com.fastaccess.github"
const val USER_LOGIN = "test@osinit.com"
const val USER_PASSWORD = "Osinit4\$"

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SmallTest
class AuthTests {
    private lateinit var device: UiDevice

    @Before
    fun startApp() {
        runApp()
        logout()
    }

     fun logout() {
        val authPage = AuthPage(device)
        if (!authPage.headerBasicAuth.exists())
        {
            val navBar = NavBar(device)
            assertThat(true, `is`(navBar.btnMenu.exists()))
            navBar.btnMenu.click()
            val menuFrame = MenuPage()
            assertThat(true, `is`(menuFrame.tabMenu.exists()))
            assertThat(true, `is`(menuFrame.tabProfile.exists()))
            menuFrame.tabProfile.click()
            menuFrame.btnLogout.clickAndWaitForNewWindow()
            assertThat(false, `is`(menuFrame.tabMenu.exists()))
            val notify = NotifyFrame(device)
            notify.check("Logout")
            notify.btnSubmit.clickAndWaitForNewWindow()
            val authPage = AuthPage(device)
            assertThat(true, `is`(authPage.btnBasicAuth.exists()))
        }
    }

     fun runApp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()
        // Wait for launcher
        val launcherPackage: String = device.launcherPackageName
        assertThat(launcherPackage, IsNull.notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )

        // Launch the app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(
            BASIC_SAMPLE_PACKAGE
        )?.let {
            // Clear out any previous instances
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT
        )
    }

    private fun changeWifiSettings(mode: Boolean) {
        runApp()
//        val wifi = context.getSystemService(Context.WIFI_SERVICE) //as WifiManager
//        wifi.isWifiEnabled = mode
        setWifiSettingsOnDevice(mode)
    }

    @Throws(UiObjectNotFoundException::class)
    fun setWifiSettingsOnDevice(enabled: Boolean) {
        val wifiSwitchSelector = UiSelector().className(android.widget.Switch::class.java.name)
        val wifiSwitch = device.findObject(wifiSwitchSelector)
        if (wifiSwitch.waitForExists(LAUNCH_TIMEOUT) && wifiSwitch.isEnabled) {
            if (wifiSwitch.isChecked != enabled) {
                wifiSwitch.click()
            }
        }
    }

    @After
    fun cleanCash(){}

    // C16893
    @Test
    fun AlayoutTest(){
        val authPage = AuthPage(device)
        authPage.check()
    }

    // C16894
    @Test
    fun BauthCorrect()
    {
        login()
        val navBar = NavBar(device)
        navBar.check()
        val tabBar = TabBar(device)
        tabBar.check()
    }

    private fun login() {
        val authPage = AuthPage(device)
        AlayoutTest()
        authPage.txtUserName.setText(USER_LOGIN)
        authPage.txtPassword.setText(USER_PASSWORD)
        assertThat(true, `is`(authPage.txtPassword.text.equals("••••••••")))
        authPage.btnHidePassword.click()
        assertThat(true, `is`(authPage.txtPassword.text.equals(USER_PASSWORD)))
        authPage.btnLogin.clickAndWaitForNewWindow()
    }

    // C16895
    @Test
    fun CauthIncorrect()
    {
        val authPage = AuthPage(device)
        AlayoutTest()
        authPage.txtUserName.setText("")
        authPage.txtPassword.setText(USER_PASSWORD)
        authPage.btnLogin.click()
        assertThat(true, `is`(authPage.errUserName.exists()))
        assertThat(true, `is`(authPage.headerBasicAuth.exists()))

        authPage.txtUserName.setText(USER_LOGIN)
        authPage.txtPassword.setText("")
        authPage.btnLogin.click()
        assertThat(true, `is`(authPage.errPassword.exists()))
        assertThat(true, `is`(authPage.headerBasicAuth.exists()))

        authPage.txtUserName.setText(USER_LOGIN)
        authPage.txtPassword.setText(USER_PASSWORD)
        assertThat(true, `is`(authPage.headerBasicAuth.exists()))
    }

    // C16896
    @Test
    fun DauthEmpty(){
        val authPage = AuthPage(device)
        AlayoutTest()
        authPage.btnLogin.click()
        assertThat(true, `is`(authPage.errUserName.exists()))
        assertThat(true, `is`(authPage.errPassword.exists()))
    }

    // C16897
    @Test
    fun EauthRepeated(){
        BauthCorrect()
        // свернуть приложение
        device.pressHome()
        runApp()
        val navBar = NavBar(device)
        navBar.check()
        val tabBar = TabBar(device)
        tabBar.check()
        logout()
        AlayoutTest()
    }

    // C18760
    @Test
    fun FnoNetworkAccess(){
        BauthCorrect()
        device.pressHome()
        changeWifiSettings(false)
        runApp()
        logout()
        login()
        val navBar = NavBar(device)
        navBar.check()
        val tabBar = TabBar(device)
        tabBar.check()
        changeWifiSettings(true)
    }
}