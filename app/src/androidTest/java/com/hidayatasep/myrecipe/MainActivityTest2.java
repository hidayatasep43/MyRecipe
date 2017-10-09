package com.hidayatasep.myrecipe;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyler_view),
                        withParent(withId(R.id.swipe_refresh)),
                        isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_ingridient), withText("- 2 CUP of Graham Cracker crumbs\n- 6 TBLSP of unsalted butter, melted\n- 0 CUP of granulated sugar\n- 1 TSP of salt\n- 5 TBLSP of vanilla\n- 1 K of Nutella or other chocolate-hazelnut spread\n- 500 G of Mascapone Cheese(room temperature)\n- 1 CUP of heavy cream(cold)\n- 4 OZ of cream cheese(softened)\n"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyler_view),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("- 2 CUP of Graham Cracker crumbs - 6 TBLSP of unsalted butter, melted - 0 CUP of granulated sugar - 1 TSP of salt - 5 TBLSP of vanilla - 1 K of Nutella or other chocolate-hazelnut spread - 500 G of Mascapone Cheese(room temperature) - 1 CUP of heavy cream(cold) - 4 OZ of cream cheese(softened) ")));


    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
