package com.igorvd.bitcoincharts

import android.app.Activity
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.igorvd.bitcoincharts.testutil.assertion.hasItemCount
import com.igorvd.bitcoincharts.testutil.matcher.NestedScrollAction
import com.igorvd.bitcoincharts.testutil.matcher.withDrawable
import com.igorvd.bitcoincharts.testutil.matcher.withIndex
import com.igorvd.bitcoincharts.testutil.matcher.withRecyclerViewChildAt
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Assert

@Suppress("TooManyFunctions", "UnnecessaryAbstractClass")
open class BaseRobot<T : BaseRobot<T>> {

    companion object {
        const val TIMEOUT = 5_000L
    }

    protected val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    fun waitForViewWithText(text: String) {
        val title = device.wait(Until.findObject(By.text(text)), TIMEOUT)
        Assert.assertNotNull("Could not find view with text $text after $TIMEOUT ms", title)
        Assert.assertEquals(
            "Could not find view with text $text after $TIMEOUT ms",
            text,
            title.text
        )
    }

    fun checkIsDisplayed(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).check(matches(isDisplayed()))
        return this
    }

    fun checkIsDisplayed(@IdRes viewId: Int, index: Int): BaseRobot<T> {
        onView(withIndex(withId(viewId), index)).check(matches(isDisplayed()))
        return this
    }

    fun checkIsHidden(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).check(matches(not(isDisplayed())))
        return this
    }

    fun checkIsHidden(@IdRes viewId: Int, index: Int): BaseRobot<T> {
        onView(withIndex(withId(viewId), index)).check(matches(not(isDisplayed())))
        return this
    }

    fun checkViewContainText(@IdRes viewId: Int, @StringRes expectedText: Int): BaseRobot<T> {
        onView(withId(viewId)).check(matches(withText(expectedText)))
        return this
    }

    fun checkViewContainText(@IdRes viewId: Int, expectedText: String): BaseRobot<T> {
        onView(withId(viewId)).check(matches(withText(expectedText)))
        return this
    }

    fun checkViewContainText(@IdRes viewId: Int, index: Int, expectedText: String): BaseRobot<T> {
        onView(withIndex(withId(viewId), index)).check(matches(withText(expectedText)))
        return this
    }

    fun checkViewContainText(@IdRes viewId: Int, index: Int, expectedTextRes: Int): BaseRobot<T> {
        onView(withIndex(withId(viewId), index)).check(matches(withText(expectedTextRes)))
        return this
    }

    fun checkWithDrawable(@IdRes viewId: Int, drawableRes: Int) = apply {
        onView(withId(viewId))
            .check(matches(withDrawable(drawableRes)))
    }

    fun clickOnView(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).perform(click())
        return this
    }

    fun clickOnView(@IdRes viewId: Int, index: Int): BaseRobot<T> {
        onView(withIndex(withId(viewId), index)).perform(click())
        return this
    }

    fun nestedScrollTo(@IdRes viewId: Int, index: Int): BaseRobot<T> {
        onView(withIndex(withId(viewId), index)).perform(NestedScrollAction())
        return this
    }

    fun checkRecyclerViewAtPositionDisplayed(
        @IdRes recyclerViewId: Int,
        @IdRes viewId: Int,
        position: Int
    ): BaseRobot<T> = apply {
        onView(withRecyclerViewChildAt(recyclerViewId, viewId, position))
            .check(matches(isDisplayed()))
    }

    fun checkRecyclerViewAtPositionWithText(
        @IdRes recyclerViewId: Int,
        @IdRes viewId: Int,
        position: Int,
        text: String
    ): BaseRobot<T> = apply {
        onView(withRecyclerViewChildAt(recyclerViewId, viewId, position))
            .check(matches(withText(text)))
    }

    fun getStringById(@StringRes stringId: Int, vararg args: Any): String {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        if (args.isNotEmpty()) {
            return context.getString(stringId, *args)
        }
        return context.getString(stringId)
    }
}