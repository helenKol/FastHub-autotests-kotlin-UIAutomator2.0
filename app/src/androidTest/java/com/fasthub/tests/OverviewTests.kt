package com.fasthub.tests

import androidx.test.InstrumentationRegistry
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.UiDevice
import com.fasthub.pages.RepositoryPage
import com.fasthub.pages.AuthPage
import com.fasthub.pages.FeedsPage
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SmallTest
class OverviewTests {
    private lateinit var device: UiDevice

    @Before
    fun auth(){
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val authTest = AuthTests()
        authTest.runApp()
        val authPage = AuthPage(device)
        if (authPage.headerBasicAuth.exists())
        {
            authTest.BauthCorrect()
        }
    }

    // 16899
    @Test
    fun AlayoutTest(){
        val feedsPage = FeedsPage(device)
        if (!feedsPage.btnReload.exists()){
            feedsPage.check()
        }
    }

    @Test
    fun BGotoRepository(){
        val feedsPage = FeedsPage(device)
        if (!feedsPage.btnReload.exists()){
            feedsPage.txtRepository.clickAndWaitForNewWindow()
            val repositoryPage = RepositoryPage()
            repositoryPage.appBar().isVisible()
            repositoryPage.toolBar().isVisible()
            repositoryPage.actionBar().checkNavigation()
            repositoryPage.TabBar().isVisible()

            repositoryPage.TabBar().btnIssues.clickAndWaitForNewWindow()
            repositoryPage.WebView().check()

            repositoryPage.TabBar().btnPullRequest.clickAndWaitForNewWindow()
            repositoryPage.WebView().check()

            repositoryPage.TabBar().btnProjects.clickAndWaitForNewWindow()
            repositoryPage.WebView().check()
        }
    }
}