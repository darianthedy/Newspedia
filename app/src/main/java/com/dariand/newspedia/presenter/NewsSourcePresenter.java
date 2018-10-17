package com.dariand.newspedia.presenter;

import android.util.Log;

import com.android.volley.Request;
import com.dariand.newspedia.model.NewsAPIClient;
import com.dariand.newspedia.model.NewsSourceData;
import com.dariand.newspedia.model.NewsSourceInteractor;
import com.dariand.newspedia.model.RequestQueueSingleton;
import com.dariand.newspedia.model.ServiceGenerator;
import com.dariand.newspedia.view.NewsSourceView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsSourcePresenter {

    private NewsSourceView newsSourceView;
    private NewsSourceInteractor newsSourceInteractor;

    public NewsSourcePresenter(NewsSourceView newsSourceView, NewsSourceInteractor newsSourceInteractor) {
        this.newsSourceView = newsSourceView;
        this.newsSourceInteractor = newsSourceInteractor;
    }

    public void onResume() {
        if(newsSourceView != null) {
            newsSourceView.showProgress();
        }

        // -- Changed to use Retrofit --
//        Request getNewsSourceDataRequest = newsSourceInteractor.getNewsSourceList(this::loadNewsSourceFinished);
//        RequestQueueSingleton.getInstance(newsSourceView.getContext()).addToRequestQueue(getNewsSourceDataRequest);

        newsSourceInteractor.getNewsSourceList(this::loadNewsSourceFinished);
    }

    public void onStop() {
    }

    public void onDestroy() {
        newsSourceView = null;
    }

    public void loadNewsSourceFinished(List<NewsSourceData> newsSourceDataList) {
        if(newsSourceView != null) {
            newsSourceView.setNewsSourceItem(newsSourceDataList);
            newsSourceView.hideProgress();
        }
    }

    public void onItemClicked(NewsSourceData newsSourceData) {
        newsSourceView.openNewsArticles(newsSourceData);
    }
}
