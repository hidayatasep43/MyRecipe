package com.hidayatasep.myrecipe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hidayatasep.myrecipe.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hidayatasep43 on 8/20/2017.
 */

public class DetailRecipeAdapter extends RecyclerView.Adapter<DetailRecipeAdapter.MyViewHolder>{

    private List<Long> mListId;

    private final int TYPE_RECEIPE = 1;
    private final int TYPE_STEP = 2;
    private Context mContext;

    public DetailRecipeAdapter(Context context, List<Long> listId) {
        mListId = listId;
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
        return mListId.size() + 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_detail_recipe) TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
