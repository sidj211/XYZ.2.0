package com.example.xyzreader.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.xyzreader.network.ArticleService;
import com.example.xyzreader.network.ServiceBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {

    private static ArticleRepository mArticleRepository;
    public static ArticleRepository getInstance()
    {
        if(mArticleRepository == null)
        {
            mArticleRepository = new ArticleRepository();
        }
        return mArticleRepository;
    }


    private ArticleService mArticleService;
    public ArticleRepository()
    {
        //making instance of the interface service with the help of it we will call asynchronous
        mArticleService = ServiceBuilder.buildService(ArticleService.class);
    }


    public LiveData<List<Article>> getArticles(){

         MutableLiveData<List<Article>> articleData = new MutableLiveData<>();

        mArticleService.getArticles().enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                articleData.postValue(response.body());
            }
            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                articleData.setValue(null);
            }
        });

        return articleData;
    }
}
