package com.example.xyzreader.network;

import com.example.xyzreader.model.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArticleService {

    @GET("data.json")
    Call<List<Article>> getArticles();
}
