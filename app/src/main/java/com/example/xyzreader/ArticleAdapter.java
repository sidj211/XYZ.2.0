package com.example.xyzreader;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xyzreader.model.Article;
import com.example.xyzreader.ui.DynamicHeightNetworkImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {
    long DURATION = 500;
    private boolean on_attach = true;


    ArrayList<Article> articleArrayList;
    ArticleClickListener mItemClickListener;

    public ArticleAdapter(ArrayList<Article> articleArrayList,ArticleClickListener mItemClickListener) {
        this.articleArrayList = articleArrayList;
        this.mItemClickListener = mItemClickListener;
    }

    public interface ArticleClickListener
    {
        void onArticleClick(Article article);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_article, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Article article = articleArrayList.get(position);

        holder.titleView.setText(article.getTitle());
        holder.thumbnailView.setAspectRatio((float) article.getAspect_ratio());
        Glide.with(holder.itemView.getContext())
                .asBitmap()
                .dontAnimate()
                .load(article.getPhoto_url())
                .into(holder.thumbnailView);
        setAnimation(holder.itemView,position);
    }

    @Override
    public int getItemCount()
    {
        if(articleArrayList.size()>0)
        return articleArrayList.size();
        else
        {
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                Log.d("TAG", "onScrollStateChanged: Called " + newState);
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        super.onAttachedToRecyclerView(recyclerView);
    }

    private void setAnimation(View itemView, int i) {
        if(!on_attach){
            i = -1;
        }
        boolean isNotFirstItem = i == -1;
        i++;
        itemView.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "alpha", 0.f, 0.5f, 1.0f);
        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
        animator.setStartDelay(isNotFirstItem ? DURATION / 2 : (i * DURATION / 3));
        animator.setDuration(500);
        animatorSet.play(animator);
        animator.start();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public DynamicHeightNetworkImageView thumbnailView;
        public TextView titleView;
        public TextView subtitleView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailView = itemView.findViewById(R.id.thumbnail);
            titleView = (TextView) itemView.findViewById(R.id.article_title);
            subtitleView = (TextView) itemView.findViewById(R.id.article_subtitle);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int id = getAdapterPosition();
            Article article = articleArrayList.get(id);
            mItemClickListener.onArticleClick(article);

        }
    }
}

