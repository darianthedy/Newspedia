package com.dariand.newspedia.presenter;

import com.android.volley.Request;
import com.dariand.newspedia.model.NewsArticlesData;
import com.dariand.newspedia.model.NewsArticlesInteractor;
import com.dariand.newspedia.model.RequestQueueSingleton;
import com.dariand.newspedia.view.NewsArticlesView;

import java.util.List;

public class NewsArticlesPresenter {

    private NewsArticlesView newsArticlesView;
    private NewsArticlesInteractor newsArticlesInteractor;

    public NewsArticlesPresenter(NewsArticlesView newsArticlesView, NewsArticlesInteractor newsArticlesInteractor) {
        this.newsArticlesView = newsArticlesView;
        this.newsArticlesInteractor = newsArticlesInteractor;
    }

    public void onResume() {
        if(newsArticlesView != null) {
            newsArticlesView.showProgress();
        }

        // -- Changed to use Retrofit --
//        Request getNewsSourceDataRequest = newsArticlesInteractor.getNewsArticlesList(newsArticlesView.getSourceId(), this::loadNewsArticlesFinished);
//        RequestQueueSingleton.getInstance(newsArticlesView.getContext()).addToRequestQueue(getNewsSourceDataRequest);

        newsArticlesInteractor.getNewsArticlesList(newsArticlesView.getSourceId(), this::loadNewsArticlesFinished);
    }

    public void onStop() {
    }

    public void onDestroy() {
        newsArticlesView = null;
    }

    public void loadNewsArticlesFinished(List<NewsArticlesData> newsArticlesDataList) {
        if(newsArticlesView != null) {
            newsArticlesView.hideProgress();
            newsArticlesView.setNewsArticlesItem(newsArticlesDataList);
        }
    }

    public void onItemClicked(NewsArticlesData newsArticlesData) {
        newsArticlesView.openArticle(newsArticlesData);
    }
}
