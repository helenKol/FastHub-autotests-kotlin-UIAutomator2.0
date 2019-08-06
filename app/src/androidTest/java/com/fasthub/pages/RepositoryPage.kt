package com.fasthub.pages

import androidx.test.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import com.fasthub.utils.findObjectById
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat

class RepositoryPage {
    private var device: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    inner class appBar {
        val panel = device.findObjectById("com.fastaccess.github:id/appbar")
        val imgAvatar = device.findObjectById("com.fastaccess.github:id/avatar")
        val txtHeader = device.findObjectById("com.fastaccess.github:id/headerTitle")
        val txtDate = device.findObjectById("com.fastaccess.github:id/date")
        val txtLanguage = device.findObjectById("com.fastaccess.github:id/language")
        val btnTags = device.findObjectById("com.fastaccess.github:id/tagsIcon")
        val btnInfo = device.findObjectById("com.fastaccess.github:id/detailsIcon")

        fun isVisible() {
            assertThat(true, `is`(panel.exists()))
            assertThat(true, `is`(imgAvatar.exists()))
            assertThat(true, `is`(txtHeader.exists()))
            assertThat(true, `is`(txtHeader.text.isNotEmpty()))
            assertThat(true, `is`(txtDate.exists()))
            assertThat(true, `is`(txtDate.text.isNotEmpty()))
            assertThat(true, `is`(txtLanguage.exists()))
            assertThat(true, `is`(txtLanguage.text.isNotEmpty()))
            assertThat(true, `is`(btnTags.exists()))
            assertThat(true, `is`(btnInfo.exists()))
        }
    }
    inner class toolBar{
            val panel = device.findObjectById("com.fastaccess.github:id/toolbar")
            val btnBack = panel.getChild(UiSelector().className("android.widget.ImageButton"))
            val imgWatchRepo = device.findObjectById("com.fastaccess.github:id/watchRepoImage")
            val valWatchRepo = device.findObjectById("com.fastaccess.github:id/watchRepo")
            val imgStarRepo = device.findObjectById("com.fastaccess.github:id/starRepoImage")
            val valStarRepo = device.findObjectById("com.fastaccess.github:id/starRepo")
            val imgForkRepo = device.findObjectById("com.fastaccess.github:id/forkRepoImage")
            val valForkRepo = device.findObjectById("com.fastaccess.github:id/forkRepo")
            val imgWikiRepo = device.findObjectById("com.fastaccess.github:id/wikiLayout").getChild(UiSelector().className("android.widget.TextView").text("Wiki"))
            val imgPinRepo = device.findObject(UiSelector().resourceId("com.fastaccess.github:id/pinText").text("Pin"))
            val txtNotifyTitle = device.findObject(UiSelector().resourceId("com.fastaccess.github:id/title").text("Fork"))
            val btnNotifyCancel = device.findObjectById("com.fastaccess.github:id/cancel")
            val btnNotifyOk = device.findObjectById("com.fastaccess.github:id/ok")
            val btnExtra = device.findObject(UiSelector().className("android.support.v7.widget.LinearLayoutCompat"))
            val btnShare = device.findObject(UiSelector().resourceId("com.fastaccess.github:id/title").text("Share"))
            val btnOpenBrowser = device.findObject(UiSelector().resourceId("com.fastaccess.github:id/title").text("Open in browser"))
            val btnCopyUrl = device.findObject(UiSelector().resourceId("com.fastaccess.github:id/title").text("Copy URL"))

            fun isVisible(){
                assertThat(true, `is`(panel.exists()))
                assertThat(true, `is`(btnBack.exists()))
                assertThat(true, `is`(imgWatchRepo.exists()))
                // check increment of watchRepoValue
                val valWatchBefore = valWatchRepo.text
                imgWatchRepo.click()
                assertThat(valWatchRepo.text, not(`is`(valWatchBefore)))
                imgWatchRepo.click()
                assertThat(valWatchRepo.text, `is`(valWatchBefore))

                assertThat(true, `is`(imgStarRepo.exists()))
                // check increment of starRepoValue
                val valStarBefore = valStarRepo.text
                imgStarRepo.click()
                assertThat(valStarRepo.text, not(`is`(valStarBefore)))
                imgStarRepo.click()
                assertThat(valStarRepo.text, `is`(valStarBefore))

                assertThat(true, `is`(imgForkRepo.exists()))
                // check increment of forkRepoValue
                val valForkBefore = valForkRepo.text
                imgForkRepo.click()
                assertThat(true, `is`(txtNotifyTitle.exists()))
                btnNotifyCancel.click()
                assertThat(valForkRepo.text, `is`(valForkBefore))
                imgForkRepo.click()
                assertThat(true, `is`(txtNotifyTitle.exists()))
                btnNotifyOk.click()
                assertThat(valForkRepo.text, not(`is`(valForkBefore)))

                assertThat(true, `is`(imgWikiRepo.exists()))
                assertThat(true, `is`(imgPinRepo.exists()))
                assertThat(true, `is`(btnExtra.exists()))

                goToExtra()
            }

            fun goToExtra(){
                btnExtra.click()
                assertThat(true, `is`(btnShare.exists()))
                assertThat(true, `is`(btnOpenBrowser.exists()))
                assertThat(true, `is`(btnCopyUrl.exists()))
                btnCopyUrl.click()
            }
        }

    inner class actionBar{
        val btnReadme = device.findObject(UiSelector().className("android.support.v7.app.ActionBar\$Tab").index(0))
            .getChild(UiSelector().text("README"))
        val btnFiles = device.findObject(UiSelector().className("android.support.v7.app.ActionBar\$Tab").index(1))
            .getChild(UiSelector().text("FILES"))
        val btnCommits = device.findObject(UiSelector().className("android.support.v7.app.ActionBar\$Tab").index(2))
            .getChild(UiSelector().textContains("COMMITS"))
        val btnReleases = device.findObject(UiSelector().className("android.support.v7.app.ActionBar\$Tab").index(3))
            .getChild(UiSelector().text("RELEASES"))
        val btnContributors = device.findObject(UiSelector().className("android.support.v7.app.ActionBar\$Tab").index(4))
            .getChild(UiSelector().text("CONTRIBUTORS"))
        fun checkNavigation(){
            assertThat(true, `is`(btnReadme.exists()))
            assertThat(true, `is`(btnReadme.isSelected))
            assertThat(true, `is`(WebView().titleReadme.exists()))
            checkActionBarButton(btnFiles)
            assertThat(true, `is`(WebView().branchList.exists()))
            assertThat(true, `is`(WebView().btnParentFolder.exists()))
            assertThat(true, `is`(WebView().btnDownload.exists()))
            assertThat(true, `is`(WebView().btnSearch.exists()))
            checkActionBarButton(btnCommits)
            assertThat(true, `is`(WebView().branchList.exists()))
            assertThat(false, `is`(WebView().btnParentFolder.exists()))
            checkActionBarButton(btnReleases)
            checkActionBarButton(btnContributors)

        }

        private fun checkActionBarButton(btn: UiObject) {
            assertThat(true, `is`(btn.exists()))
            assertThat(false, `is`(btn.isSelected))
            btn.click()
            assertThat(true, `is`(btn.isSelected))
        }
    }

    inner class WebView{
        val titleReadme = device.findObject(UiSelector().className("android.view.View").description("About"))
        val branchList = device.findObjectById("com.fastaccess.github:id/branches")
        val btnParentFolder = device.findObjectById("com.fastaccess.github:id/toParentFolder")
        val btnDownload = device.findObjectById("com.fastaccess.github:id/downloadRepoFiles")
        val btnSearch = device.findObjectById("com.fastaccess.github:id/searchRepoFiles")
        val btnOpened = device.findObject(UiSelector().className("android.support.v7.app.ActionBar\$Tab"))
            .getChild(UiSelector().className("android.widget.TextView").textContains("OPENED"))
        val btnClosed = device.findObject(UiSelector().className("android.support.v7.app.ActionBar\$Tab"))
            .getChild(UiSelector().className("android.widget.TextView").textContains("CLOSED"))

        fun check(){
            assertThat(false, `is`(WebView().titleReadme.exists()))
            assertThat(true, `is`(WebView().btnOpened.exists()))
            assertThat(true, `is`(WebView().btnClosed.exists()))
        }
    }

    inner class TabBar{
        val btnCode = device.findObjectById("com.fastaccess.github:id/code")
        val btnIssues = device.findObjectById("com.fastaccess.github:id/issues")
        val btnPullRequest = device.findObjectById("com.fastaccess.github:id/pullRequests")
        val btnProjects = device.findObjectById("com.fastaccess.github:id/projects")

        fun isVisible(){
            assertThat(true, `is`(btnCode.exists()))
            assertThat(true, `is`(btnIssues.exists()))
            assertThat(true, `is`(btnPullRequest.exists()))
            assertThat(true, `is`(btnProjects.exists()))
        }
    }
}