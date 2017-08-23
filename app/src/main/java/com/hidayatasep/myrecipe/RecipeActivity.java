package com.hidayatasep.myrecipe;

import android.net.Uri;
import android.os.Bundle;


import com.hidayatasep.myrecipe.base.BaseActivity;
import com.hidayatasep.myrecipe.fragment.RecipeFragment;
import com.hidayatasep.myrecipe.model.Recipe;

import timber.log.Timber;

public class RecipeActivity extends BaseActivity implements RecipeFragment.OnRecipeInteractionListener {

    Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        /*
        id = getIntent().getLongExtra("id", -1);
        mRecipe = Recipe.getRecipeById(id, realm);
        Timber.d(mRecipe.mListSteps.size() + "");

        RecipeFragment recipeFragment = RecipeFragment.newInstance(id);
        getFragmentManager().beginTransaction()
                .add(R.id.frame_layout, recipeFragment)
                .commit();
        */

    }

    @Override
    public void onRecipeInteraction(Uri uri) {

    }
}
