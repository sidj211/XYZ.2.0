package com.example.xyzreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.example.xyzreader.model.Article;

public class ArticleDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature((Window.FEATURE_CONTENT_TRANSITIONS));
        setContentView(R.layout.activity_article_detail);
        postponeEnterTransition();
        Intent intent = getIntent();
        Article article = intent.getExtras().getParcelable("article");
        Log.e("PAKDO","checking log"+article.getTitle());
        //sending data in the constructor
        ArticleDetailFragment articleDetailFragment = new ArticleDetailFragment(article);
        getSupportFragmentManager().beginTransaction().replace(R.id.up_container,articleDetailFragment).commit();

    }
}
