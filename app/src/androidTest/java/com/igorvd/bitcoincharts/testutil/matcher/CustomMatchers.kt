package com.igorvd.bitcoincharts.testutil.matcher

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun withIndex(matcher: Matcher<View>, index: Int): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        private var currentIndex = 0

        override fun describeTo(description: Description) {
            description.appendText("with index: ")
            description.appendValue(index)
            matcher.describeTo(description)
        }

        override fun matchesSafely(view: View): Boolean {
            return matcher.matches(view) && currentIndex++ == index
        }
    }
}

fun withRecyclerViewChildAt(
    @IdRes recyclerViewRes: Int,
    @IdRes childId: Int,
    position: Int
): Matcher<View> {
    return RecyclerViewMatcher(recyclerViewRes)
        .atPositionOnView(position, childId)
}

fun clickChildViewWithId(id: Int): ViewAction {
    return object : ViewAction {
        override fun perform(uiController: UiController, view: View) {
            val childView = view.findViewById<View>(id)
            childView.performClick()
        }

        override fun getConstraints(): Matcher<View>? {
            return null
        }

        override fun getDescription(): String {
            return "Click on a child view with specified id."
        }
    }
}