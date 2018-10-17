package com.dariand.newspedia.view;

import android.content.Context;

import com.dariand.newspedia.model.NewsArticlesData;

import java.util.List;

public interface NewsArticlesView {
    void showProgress();
    void hideProgress();
    Context getContext();

    String getSourceId();
    void setNewsArticlesItem(List<NewsArticlesData> newsArticlesDataList);
    void openArticle(NewsArticlesData newsArticlesData);
}
