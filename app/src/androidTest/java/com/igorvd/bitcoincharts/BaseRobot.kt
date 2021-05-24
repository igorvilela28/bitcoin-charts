package com.igorvd.bitcoincharts

import android.app.Activity
import android.view.View
import android.webkit.WebView
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.hasTextColor
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.igorvd.bitcoincharts.testutil.assertion.hasItemCount
import com.igorvd.bitcoincharts.testutil.matcher.NestedScrollAction
import com.igorvd.bitcoincharts.testutil.matcher.clickChildViewWithId
import com.igorvd.bitcoincharts.testutil.matcher.withIndex
import com.igorvd.bitcoincharts.testutil.matcher.withRecyclerViewChildAt
import org.hamcrest.Description
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.hamcrest.core.AllOf
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

    fun checkDoesNotExist(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).check(doesNotExist())
        return this
    }

    fun checkHasVisibility(
        @IdRes viewId: Int,
        visibility: ViewMatchers.Visibility
    ): BaseRobot<T> {
        onView(withId(viewId)).check(matches(withEffectiveVisibility(visibility)))
        return this
    }

    fun enterTextIntoView(@IdRes viewId: Int, text: String): BaseRobot<T> {
        onView(withId(viewId)).perform(typeText(text))
        return this
    }

    fun enterTextIntoView(@IdRes viewId: Int, index: Int, text: String): BaseRobot<T> {
        onView(withIndex(withId(viewId), index)).perform(typeText(text))
        return this
    }

    fun checkTextInDialogDisplayed(@StringRes messageResId: Int): BaseRobot<T> {
        onView(withText(messageResId)).inRoot(isDialog()).check(matches(isDisplayed()))
        return this
    }

    fun checkTextInDialogNotDisplayed(@StringRes messageResId: Int): BaseRobot<T> {
        onView(withText(messageResId)).check(doesNotExist())
        return this
    }

    fun checkFieldWithHintIsDisplayed(@StringRes messageResId: String): BaseRobot<T> {
        onView(withHint(messageResId)).check(matches(isDisplayed()))
        return this
    }

    fun checkViewHasHint(@IdRes viewId: Int, @StringRes messageResId: String): BaseRobot<T> {
        onView(withId(viewId)).check(matches(withHint(messageResId)))
        return this
    }

    fun checkViewContainText(text: String): BaseRobot<T> {
        onView(withText(text)).check(matches(isDisplayed()))
        return this
    }

    fun checkViewContainSubText(subText: String): BaseRobot<T> {
        onView(withSubstring(subText)).check(matches(isDisplayed()))
        return this
    }

    fun checkViewContainSubText(@IdRes viewId: Int, subText: String): BaseRobot<T> {
        onView(withId(viewId)).check(matches(withSubstring(subText)))
        return this
    }

    fun checkViewContainText(textRes: Int): BaseRobot<T> {
        onView(withText(textRes)).check(matches(isDisplayed()))
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

    fun checkViewHasSiblingWithText(@IdRes viewId: Int, @IdRes text: Int): BaseRobot<T> {
        onView(allOf(withId(viewId), hasSibling(withText(text))))
        return this
    }

    fun checkViewContainTextInContentDescription(text: String): BaseRobot<T> {
        onView(withContentDescription(text)).check(matches(isDisplayed()))
        return this
    }

    fun checkViewHasTextColor(@IdRes viewId: Int, expectedColorRes: Int): BaseRobot<T> {
        onView(withId(viewId)).check(matches(hasTextColor(expectedColorRes)))
        return this
    }

    fun checkViewHasTextColor(
        @IdRes viewId: Int,
        index: Int,
        expectedColorRes: Int
    ): BaseRobot<T> {
        onView(withIndex(withId(viewId), index))
            .check(matches(hasTextColor(expectedColorRes)))
        return this
    }

    fun checkIsClickable(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).check(matches(isClickable()))
        return this
    }

    fun clickOnView(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).perform(click())
        return this
    }

    fun clickOnView(@IdRes viewId: Int, index: Int): BaseRobot<T> {
        onView(withIndex(withId(viewId), index)).perform(click())
        return this
    }

    fun checkCheckboxChecked(@IdRes viewId: Int, index: Int): BaseRobot<T> {
        onView(withIndex(withId(viewId), index)).check(matches(isChecked()))
        return this
    }

    fun checkCheckboxNotChecked(@IdRes viewId: Int, index: Int): BaseRobot<T> {
        onView(withIndex(withId(viewId), index)).check(matches(not(isChecked())))
        return this
    }

    fun clickOnViewText(viewText: String): BaseRobot<T> {
        onView(withText(viewText)).perform(click())
        return this
    }

    fun clickOnHasSiblingViewWithId(@IdRes viewId: Int, @IdRes text: Int): BaseRobot<T> {
        onView(allOf(withId(text), withParent(withId(viewId)))).perform(click())
        return this
    }

    fun checkIsDisabled(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).check(matches(not(isEnabled())))
        return this
    }

    fun checkIsEnabled(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).check(matches(isEnabled()))
        return this
    }

    fun scrollViewDown(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).perform(swipeUp(), click())
        return this
    }

    fun scrollViewUp(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).perform(swipeDown(), click())
        return this
    }

    fun swipeLeftOnView(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).perform(swipeLeft())
        return this
    }

    fun swipeRightOnView(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).perform(swipeRight())
        return this
    }

    fun swipeDownOnView(@IdRes viewId: Int): BaseRobot<T> = apply {
        onView(withId(viewId)).perform(swipeDown())
    }

    fun checkViewWithTextIsDisplayedAndChecked(text: String): BaseRobot<T> {
        onView(AllOf.allOf(withText(text), isDisplayed()))
            .check(matches(isChecked()))
        return this
    }

    fun clickOnDisplayedViewWithText(text: String): BaseRobot<T> {
        onView(AllOf.allOf(withText(text), isDisplayed()))
            .perform(click())
        return this
    }

    fun scrollTo(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).perform(scrollTo())
        return this
    }

    fun nestedScrollTo(@IdRes viewId: Int, index: Int): BaseRobot<T> {
        onView(withIndex(withId(viewId), index)).perform(NestedScrollAction())
        return this
    }

    fun scrollToAndClick(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).perform(scrollTo(), click())
        return this
    }

    fun scrollToAndClickInViewWithText(@StringRes viewText: Int): BaseRobot<T> {
        onView(withText(viewText)).perform(scrollTo(), click())
        return this
    }

    fun assertItTakeMeToScreen(targetClass: Class<*>) {
        intended(hasComponent(targetClass.name))
    }

    fun assertItTakeMeToScreen(extra: String) {
        intended(allOf(hasAction(extra)))
    }

    fun closeKeyboard(): BaseRobot<T> {
        Espresso.closeSoftKeyboard()
        return this
    }

    inline fun <reified A : Activity> checkActualScreen(): BaseRobot<T> {
        intended(hasComponent(A::class.java.name))
        return this
    }

    fun withContentDescription(contentDesc: String) =
        object : BoundedMatcher<View, View>(WebView::class.java) {

            override fun describeTo(description: Description?) {
                description?.appendText("checking if content description is $contentDesc")
            }

            override fun matchesSafely(item: View?) = if (item != null) {
                item.contentDescription == contentDesc
            } else {
                false
            }
        }

    fun clickDialogButtonWithText(@StringRes textResId: Int): BaseRobot<T> {
        onView(withText(textResId)).inRoot(isDialog()).perform(click())
        return this
    }

    //region: recycler view assertions


    fun clickRecyclerViewItemText(viewText: String, viewId: Int) = apply {
        onView(withId(viewId))
            .perform(actionOnItem<RecyclerView.ViewHolder>(withText(viewText), click()))
    }

    fun clickRecyclerViewItemId(@IdRes childViewId: Int, viewId: Int, position: Int) = apply {
        onView(withId(viewId))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    position,
                    clickChildViewWithId(childViewId)
                )
            )
    }

    fun checkRecyclerViewAtPositionDisplayed(
        @IdRes recyclerViewId: Int,
        @IdRes viewId: Int,
        position: Int
    ): BaseRobot<T> = apply {
        onView(withRecyclerViewChildAt(recyclerViewId, viewId, position))
            .check(matches(isDisplayed()))
    }

    fun checkRecyclerViewAtPositionHidden(
        @IdRes recyclerViewId: Int,
        @IdRes viewId: Int,
        position: Int
    ): BaseRobot<T> = apply {
        onView(withRecyclerViewChildAt(recyclerViewId, viewId, position))
            .check(matches(not(isDisplayed())))
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

    fun clickAtPosition(
        @IdRes recyclerViewId: Int,
        @IdRes viewId: Int,
        position: Int
    ): BaseRobot<T> = apply {
        onView(withRecyclerViewChildAt(recyclerViewId, viewId, position))
            .perform(click())
    }

    fun <VH : RecyclerView.ViewHolder> scrollRecyclerViewToPosition(
        @IdRes recyclerViewId: Int,
        position: Int
    ): BaseRobot<T> = apply {
        onView(withId(recyclerViewId)).perform(RecyclerViewActions.scrollToPosition<VH>(position))
    }

    fun checkRecyclerViewCount(@IdRes recyclerViewId: Int, count: Int): BaseRobot<T> = apply {
        onView(withId(recyclerViewId)).check(hasItemCount(count))
    }

    fun checkRecyclerViewCount(@IdRes recyclerViewId: Int, index: Int, count: Int): BaseRobot<T> = apply {
        onView(withIndex(withId(recyclerViewId), index)).check(hasItemCount(count))
    }

    // endregion

    fun getStringById(@StringRes stringId: Int, vararg args: Any): String {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        if (args.isNotEmpty()) {
            return context.getString(stringId, *args)
        }
        return context.getString(stringId)
    }
}