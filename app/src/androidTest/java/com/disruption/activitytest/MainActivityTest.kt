package com.disruption.activitytest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun beforeActivityLaunched() {
        Intents.init()
    }

    @After
    fun afterActivityFinished() {
        Intents.release()
    }

    @Test
    fun activityLaunch() {
        onView(withId(R.id.button_main)).perform(click())
        onView(withId(R.id.text_header)).check(matches(isDisplayed()))
        onView(withId(R.id.button_second)).perform(click())
        onView(withId(R.id.text_header_reply)).check(matches(isDisplayed()))
    }

    @Test
    fun textInputOutput() {
        onView(withId(R.id.editText_main)).perform(typeText("This is a drill!"))
        onView(withId(R.id.button_main)).perform(click())
        onView(withId(R.id.text_message)).check(matches(withText("This is a drill!")))
    }

    @Test
    fun clickSendButtonOpensSecondActivity() {
        onView(withId(R.id.button_main)).perform(click())
        intended(hasComponent(SecondActivity::class.java.name))
    }
}