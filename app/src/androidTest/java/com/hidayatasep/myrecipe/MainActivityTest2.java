package com.hidayatasep.myrecipe;


import android.content.res.Resources;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.hidayatasep.myrecipe.adapter.DetailStepAdapter;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest2 {

    private IdlingResource mIdlingResource;

    @Rule
    public IntentsTestRule<MainActivity> mActivityTestRule = new IntentsTestRule<MainActivity>(
            MainActivity.class);

    @Before
    public void registerIdlingResource(){
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        // To prove that the test fails, omit this call:
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void recipe_Selected_Test() {

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyler_view),
                        withParent(withId(R.id.swipe_refresh)),
                        isDisplayed()));

        recyclerView.perform(actionOnItemAtPosition(0, click()));

        intended(hasComponent(RecipeActivity.class.getName()));

        onView(allOf(withId(R.id.recyler_view))).perform(actionOnItemAtPosition(1, click()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(allOf(withId(R.id.video_player), isDisplayed()));

        onView(withRecyclerView(R.id.recyler_view)
                .atPositionOnView(1, R.id.tv_detail_recipe))
                .check(matches(withText("Recipe Introduction")));

    }


    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {

        return new RecyclerViewMatcher(recyclerViewId);
    }



}
