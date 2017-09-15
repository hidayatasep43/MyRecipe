package com.hidayatasep.myrecipe.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hidayatasep.myrecipe.R;
import com.hidayatasep.myrecipe.adapter.DetailRecipeAdapter;
import com.hidayatasep.myrecipe.base.BaseFragment;
import com.hidayatasep.myrecipe.model.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeFragment extends BaseFragment implements DetailRecipeAdapter.OnReceipeClickListener {

    private static final String ARG_PARAM1 = "recipe";

    private OnRecipeInteractionListener mListener;

    @BindView(R.id.recyler_view) RecyclerView mRecyclerView;

    private Recipe mRecipe;

    public RecipeFragment() {
        // Required empty public constructor
    }

    public static RecipeFragment newInstance(Recipe recipe) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, recipe);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRecipe = getArguments().getParcelable(ARG_PARAM1);
        }

    }

    @Override
    protected View onBaseCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, view);

        DetailRecipeAdapter adapter = new DetailRecipeAdapter(getActivity(), mRecipe);
        adapter.setListener(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRecipeInteractionListener) {
            mListener = (OnRecipeInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onReceipeItemClick(int position) {
        showToast("Posisi " + position);
        if (mListener != null) {
            mListener.onRecipeInteraction(position);
        }
    }

    public void setListener(OnRecipeInteractionListener listener) {
        mListener = listener;
    }

    public interface OnRecipeInteractionListener {
        void onRecipeInteraction(int position);
    }
}
