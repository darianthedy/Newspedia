package com.dariand.newspedia.presenter;

import android.view.KeyEvent;
import android.view.View;

import com.dariand.newspedia.model.NewsArticlesData;
import com.dariand.newspedia.view.ArticleWebView;

public class ArticleWebViewPresenter {

    private ArticleWebView articleWebView;

    public ArticleWebViewPresenter(ArticleWebView articleWebView) {
        this.articleWebView = articleWebView;
    }

    public void onCreate() {
        if(articleWebView != null) {
            articleWebView.loadArticle();
        }
    }

    public void onStop() {
        articleWebView = null;
    }
}
