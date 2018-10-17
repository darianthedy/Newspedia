package com.dariand.newspedia.model;

import com.dariand.newspedia.config.AppConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsAPIClient {

    @GET("sources?" + AppConfig.API_KEY)
    Call<NewsSourceContainerData> getNewsSourceList();

    @GET("everything?" + AppConfig.API_KEY)
    Call<NewsArticlesContainerData> getNewsArticles(
            @Query(AppConfig.NEWS_SOURCES) String source
    );
}
