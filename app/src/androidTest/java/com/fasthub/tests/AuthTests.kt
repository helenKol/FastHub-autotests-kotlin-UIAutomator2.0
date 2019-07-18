package com.fasthub.tests

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.fasthub.pages.AuthPage
import com.fasthub.pages.FeedsPage
import com.fasthub.pages.MenuPage
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


const val LAUNCH_TIMEOUT: Long = 5_000L
const val DEFAULT_IDLE_TIMEOUT: Long = 2_000L
const val BASIC_SAMPLE_PACKAGE = "com.fastaccess.github"

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class AuthTests() {
    private lateinit var device: UiDevice

    @Before
    fun startApp() {
        runApp()
        authIsPossible()
    }

    private fun authIsPossible() {
        val authPage = AuthPage(device)
        assertThat(true, `is`(authPage.btnAuthLaunch.exists()))
        authPage.btnAuthLaunch.clickAndWaitForNewWindow()
    }

    private fun runApp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())!!
//        device.pressHome()
//        // Wait for launcher
//        val launcherPackage: String = device.launcherPackageName
//        assertThat(launcherPackage, IsNull.notNullValue())
//        device.wait(
//            Until.hasObject(By.pkg(launcherPackage).depth(0)),
//            LAUNCH_TIMEOUT
//        )
//
//        // Launch the app
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        val intent = context.packageManager.getLaunchIntentForPackage(
//            BASIC_SAMPLE_PACKAGE
//        )?.let {
//            // Clear out any previous instances
//            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        }
//        context.startActivity(intent)
//
//        // Wait for the app to appear
//        device.wait(
//            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
//            LAUNCH_TIMEOUT
//        )
    }

    private fun changeWifiSettings(mode: Boolean) {
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
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        val intent = context.packageManager.getLaunchIntentForPackage(
//            BASIC_SAMPLE_PACKAGE
//        )?.let {
//            // Clear out any previous instances
//            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        }
//        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT
        )

//        val wifi = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
//        wifi.isWifiEnabled = mode
    }

    @After
    fun cleanCash(){}

    // C16893
    @Test
    fun AlayoutTest(){
        val authPage = AuthPage(device)
        assertThat(true, `is`(authPage.headerBasicAuth.exists()))
        assertThat(true, `is`(authPage.headerUserName.text == "Username"))
        assertThat(true, `is`(authPage.txtUserName.text.isEmpty()))
        assertThat(true, `is`(authPage.txtUserName.isFocusable))
        assertThat(true, `is`(authPage.txtPassword.exists()))
        assertThat(true, `is`(authPage.btnHidePassword.exists()))
        assertThat(true, `is`(authPage.btnLogin.exists()))
    }

    // C16894
    @Test
    fun BauthCorrect()
    {
        enterAuthDataCorrect()
        val feedsPage = FeedsPage(device)
        assertThat(true, `is`(feedsPage.header.exists()))
    }

    private fun enterAuthDataCorrect() {
        val authPage = AuthPage(device)
        AlayoutTest()
        authPage.txtUserName.setText("test@osinit.com")
        authPage.txtPassword.setText("Osinit4\$")
        assertThat(true, `is`(authPage.txtPassword.text.equals("••••••••")))
        authPage.btnHidePassword.click()
        assertThat(true, `is`(authPage.txtPassword.text.equals("Osinit4\$")))
        authPage.btnLogin.clickAndWaitForNewWindow()
    }

    // C16895
    @Test
    fun CauthIncorrect()
    {
        val authPage = AuthPage(device)
        AlayoutTest()
        authPage.txtUserName.setText("tes1t@osinit.com")
        authPage.txtPassword.setText("Osinit4\$1")
        assertThat(true, `is`(authPage.headerBasicAuth.exists()))

        authPage.txtUserName.setText("test@osinit.com")
        authPage.txtPassword.setText("Osinit4\$1")
        assertThat(true, `is`(authPage.headerBasicAuth.exists()))

        authPage.txtUserName.setText("tes1t@osinit.com")
        authPage.txtPassword.setText("Osinit4\$")
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
        device.pressHome()
        runApp()
        var feedsPage = FeedsPage(device)
        assertThat(true, `is`(feedsPage.header.exists()))
        feedsPage.btnMenu.click()
        val menu = MenuPage(device)
        assertThat(true, `is`(menu.tabProfile.exists()))
        menu.tabProfile.click()
        menu.btnLogout.clickAndWaitForNewWindow()
        feedsPage = FeedsPage(device)
        assertThat(true, `is`(feedsPage.notifyField.exists()))
        assertThat(true, `is`(feedsPage.notifyTitle.text.equals("Logout")))
        feedsPage.btnNotifySubmit.clickAndWaitForNewWindow()
        authIsPossible()
        AlayoutTest()
    }

    // C18760
    @Test
    fun FnoNetworkAccess(){
        BauthCorrect()
        device.pressHome()
        changeWifiSettings(false)
        runApp()
        authIsPossible()
        enterAuthDataCorrect()
        val feedsPage = FeedsPage(device)
        assertThat(false, `is`(feedsPage.header.exists()))
        changeWifiSettings(true)
    }
}