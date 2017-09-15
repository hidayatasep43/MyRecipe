package com.hidayatasep.myrecipe;

import android.net.Uri;
import android.os.Bundle;
import com.hidayatasep.myrecipe.base.BaseActivity;
import com.hidayatasep.myrecipe.fragment.DetailFragment;
import com.hidayatasep.myrecipe.fragment.RecipeFragment;
import com.hidayatasep.myrecipe.model.Recipe;

import timber.log.Timber;


public class RecipeActivity extends BaseActivity implements RecipeFragment.OnRecipeInteractionListener {

    Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        if(getIntent() != null){
            mRecipe = getIntent().getParcelableExtra(MainActivity.RECIPE);
        }

        if(mRecipe == null){
            finish();
        }

        RecipeFragment recipeFragment = RecipeFragment.newInstance(mRecipe);
        recipeFragment.setListener(this);
        getFragmentManager().beginTransaction()
                .add(R.id.frame_layout, recipeFragment)
                .commit();

        if(findViewById(R.id.frame_detail_layout) != null){

        }


    }

    @Override
    public void onRecipeInteraction(int position) {
        Timber.d("Click");
        DetailFragment detailFragment = DetailFragment.newInstance("","");
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, detailFragment)
                .addToBackStack(null)
                .commit();

    }
}
