package com.example.xyzreader.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.xyzreader.model.Article;
import com.example.xyzreader.model.ArticleRepository;

import java.util.List;

public class ArticleListViewModel extends ViewModel {


    private LiveData<List<Article>> articleList;
    private ArticleRepository mArticleRepository;

    public void init()
    {
        if(articleList !=null)
        {
            return;
        }
        mArticleRepository = ArticleRepository.getInstance();
        articleList = mArticleRepository.getArticles();

    }




    public LiveData<List<Article>> getArticleList() {
        return articleList;
    }
}
