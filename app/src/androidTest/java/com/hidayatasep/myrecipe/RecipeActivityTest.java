package com.hidayatasep.myrecipe;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Created by hidayatasep43 on 10/4/2017.
 */
@RunWith(AndroidJUnit4.class)
public class RecipeActivityTest {

    @Rule
    public ActivityTestRule<RecipeActivity> mRecipeActivityActivityTestRule = new
            ActivityTestRule<RecipeActivity>(RecipeActivity.class);

    @Before
    public void init(){
        mRecipeActivityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }



}
