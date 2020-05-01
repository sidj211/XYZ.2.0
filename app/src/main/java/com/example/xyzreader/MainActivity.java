package com.example.xyzreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.example.xyzreader.model.Article;
import com.example.xyzreader.network.ArticleService;
import com.example.xyzreader.network.ServiceBuilder;
import com.example.xyzreader.ui.ArticleListViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements ArticleAdapter.ArticleClickListener {

    //
    RecyclerView mArticleRecyclerView;
    ArrayList<Article> articleArrayList = new ArrayList<>();
    ArticleAdapter newsAdapter;
    RecyclerView rvHeadline;


    ArticleListViewModel articleListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvHeadline = findViewById(R.id.recycler_view);

        articleListViewModel = ViewModelProviders.of(this).get(ArticleListViewModel.class);
        articleListViewModel.init();
        articleListViewModel.getArticleList().observe(this, articles -> {
            List<Article> newsArticles = articles;
            Log.e("PAKDO","checking lo fasdfg"+articles.size());
            articleArrayList = new ArrayList<>(articles);
            setupRecyclerView();

            Log.e("PAKDO","checking log"+articleArrayList.size());
        });



    }

    private void setupRecyclerView() {
        if (newsAdapter == null) {
            Log.e("PAKDO","checking log 1"+articleArrayList.size());

            int columnCount = getResources().getInteger(R.integer.list_column_count);
            StaggeredGridLayoutManager sglm =
                    new StaggeredGridLayoutManager(columnCount, StaggeredGridLayoutManager.VERTICAL);
            newsAdapter = new ArticleAdapter(articleArrayList,this);
            rvHeadline.setAdapter(newsAdapter);

            rvHeadline.setLayoutManager(sglm);


        } else {
            newsAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onArticleClick(Article article) {

        Intent intent = new Intent(this,ArticleDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("article",article);
        Pair[] pair = new Pair[1];
        pair[0] = new Pair<View,String>(findViewById(R.id.thumbnail),"shared_image");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,pair);
        intent.putExtras(bundle);
        startActivity(intent,options.toBundle());
    }
}
