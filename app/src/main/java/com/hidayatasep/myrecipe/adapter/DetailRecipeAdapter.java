package com.hidayatasep.myrecipe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hidayatasep.myrecipe.R;
import com.hidayatasep.myrecipe.model.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hidayatasep43 on 8/20/2017.
 */

public class DetailRecipeAdapter extends RecyclerView.Adapter<DetailRecipeAdapter.MyViewHolder>{

    private Recipe mRecipe;
    private final int TYPE_RECEIPE = 1;
    private final int TYPE_STEP = 2;
    private Context mContext;

    static OnReceipeClickListener mListener;

    public interface OnReceipeClickListener{
        public void onReceipeItemClick(int position);
    }

    public DetailRecipeAdapter(Context context, Recipe recipe) {
        mRecipe = recipe;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_RECEIPE;
        }else {
            return TYPE_STEP;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_detail_recipe, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(holder.getItemViewType() == TYPE_RECEIPE){
            holder.mTextView.setText(R.string.recipe_ingredients);
        }else{
            holder.mTextView.setText(String.format(mContext.getString(R.string.recipe_label), position));
        }
    }

    @Override
    public int getItemCount() {
        return  (mRecipe.getStepsList().size() + 1);
    }

    public void setListener(OnReceipeClickListener mListener) {
        DetailRecipeAdapter.mListener = mListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        @BindView(R.id.tv_detail_recipe) TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mListener != null){
                mListener.onReceipeItemClick(getAdapterPosition());
            }
        }
    }

}
