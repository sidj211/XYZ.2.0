package com.example.xyzreader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.xyzreader.model.Article;


public class ArticleDetailFragment extends Fragment {

    ImageView article_photo;
    TextView article_title;
    TextView article_body;
    String title;
    String body;
    String imageurl;

    public ArticleDetailFragment(Article article) {
        title = article.getTitle();
        body = article.getBody();
        imageurl = article.getPhoto_url();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_detail,container,false);


        article_title = view.findViewById(R.id.article_title);
        article_body = view.findViewById(R.id.article_body);
        article_photo = view.findViewById(R.id.photo);
        article_title.setText(title);
        getActivity().startPostponedEnterTransition();
        Glide.with(view).asBitmap().dontAnimate().load(imageurl).into(article_photo);
        article_body.setText(body);

        return view;

    }
}
