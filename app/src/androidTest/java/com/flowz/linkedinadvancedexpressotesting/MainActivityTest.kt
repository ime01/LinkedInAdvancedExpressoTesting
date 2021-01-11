package com.flowz.linkedinadvancedexpressotesting

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.ComponentNameMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @get:Rule
    var activityRule = IntentsTestRule(MainActivity::class.java)
//    var activityRule = ActivityTestRule(IdeasActivity::class.java, true, false)
    @Test
    fun punnyLaunchActivity(){
      onView(withId(R.id.button_punny))
          .perform(click())
    onView(withId(R.id.theme))
        .check(matches(withText(R.string.theme_punny)))
    }

    @Test
    fun punnyIntended(){
        onView(withId(R.id.button_punny))
            .perform(click())
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val theme = context.getString(R.string.theme_punny)
        Intents.intended(hasExtra(IdeasActivity.KEY_THEME, theme))
        Intents.intended(IntentMatchers.hasComponent(ComponentNameMatchers.hasClassName("com.flowz.linkedinadvancedexpressotesting.IdeasActivity")))

    }

    @Test
    fun punnyIntending(){
        val name = "Catalie Portman"
        val intent = Intent()
        intent.putExtra(IdeasActivity.KEY_NAME, name)
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, intent)

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val theme = context.getString(R.string.theme_punny)
        Intents.intending(hasExtra(IdeasActivity.KEY_THEME, theme)).respondWith(result)

        onView(withId(R.id.button_punny))
            .perform(click())
        
        onView(withId(R.id.name))
            .check(matches(withText(name)))
    }


//    fun anyIntent(): Matcher<Intent?>? {
//        return object : TypeSafeMatcher<Intent?>() {
//            override fun describeTo(description: Description) {
//                description.appendText("any intent")
//            }
//
//            override fun matchesSafely(intent: Intent?): Boolean {
//                return true
//            }
//        }
//    }
}