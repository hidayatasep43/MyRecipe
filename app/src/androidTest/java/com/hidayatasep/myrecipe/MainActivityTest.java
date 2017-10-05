package com.hidayatasep.myrecipe;

import android.content.Intent;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hidayatasep.myrecipe.base.BaseActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.Is.is;

/**
 * Created by hidayatasep43 on 9/17/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest

public class MainActivityTest{

    private static final String TEXT_ITEM_30 = "item: 30";

    private static final String TEXT_ITEM_30_SELECTED = "30";

    private static final String TEXT_ITEM_60 = "item: 60";

    // Match the last item by matching its text.
    private static final String LAST_ITEM_ID = "item: 99";


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    /**
     * Test that the list is long enough for this sample, the last item shouldn't appear.
     */
    @Test
    public void lastItem_NotDisplayed() {
        // Last item should not exist if the list wasn't scrolled down.
        onView(withText(LAST_ITEM_ID)).check(doesNotExist());
    }

    /**
     * Check that the item is created. onData() takes care of scrolling.
     */
    @Test
    public void list_Scrolls() {
        onRow(LAST_ITEM_ID).check(matches(isCompletelyDisplayed()));
    }

    /**
     * Clicks on a row and checks that the activity detected the click.
     */
    @Test
    public void row_Click() {
        // Click on one of the rows.
        onRow(TEXT_ITEM_30).onChildView(withId(R.id.tv_recipe)).perform(click());

    }

    private static DataInteraction onRow(String str) {
        return onData(hasEntry(equalTo(MainActivity.ROW_TEXT), is(str)));
    }

}
