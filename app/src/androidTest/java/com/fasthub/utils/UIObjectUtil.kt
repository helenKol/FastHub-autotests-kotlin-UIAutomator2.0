package com.fasthub.utils

import androidx.test.uiautomator.*

fun UiDevice.findObjectById(resourceId: String): UiObject {
    this.wait(Until.findObject(By.res(resourceId)), 5_000)
    return findObject(UiSelector().resourceId(resourceId))
}

fun UiDevice.findObjectByText(text: String): UiObject {
    this.wait(Until.findObject(By.text(text)), 10_000)
    return findObject(UiSelector().text(text))
}

fun UiDevice.findObjectByText(text: String, className: String): UiObject {
    return findObject(UiSelector().text(text).className(className))
}

fun UiDevice.findObjectById2(resourceId: String): UiObject2 = findObject(By.res(resourceId))

fun UiDevice.findObjectByText2(text: String): UiObject2 = findObject(By.text(text))

fun UiDevice.findObjectByText2(text: String, className: String): UiObject2 {
    return findObject(By.text(text).clazz(className))
}
