package com.dariand.newspedia.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.dariand.newspedia.R;
import com.dariand.newspedia.config.AppConfig;
import com.dariand.newspedia.model.NewsSourceData;
import com.dariand.newspedia.model.NewsSourceInteractor;
import com.dariand.newspedia.presenter.NewsSourcePresenter;
import com.dariand.newspedia.view.NewsSourceAdapter;
import com.dariand.newspedia.view.NewsSourceView;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class NewsSourceActivity extends AppCompatActivity implements NewsSourceView {

    @Inject
    NewsSourceInteractor newsSourceInteractor;

    private NewsSourcePresenter newsSourcePresenter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_source);

        newsSourcePresenter = new NewsSourcePresenter(this, newsSourceInteractor);

        initializeView();
    }

    @Override
    protected void onResume() {
        newsSourcePresenter.onResume();
        super.onResume();
    }

    @Override
    protected void onStop() {
        newsSourcePresenter.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        newsSourcePresenter.onDestroy();
        super.onDestroy();
    }

    private void initializeView() {
        recyclerView = findViewById(R.id.newsSourceRecyclerView);
        progressBar = findViewById(R.id.progress);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setNewsSourceItem(List<NewsSourceData> newsSourceDataList) {
        recyclerView.setAdapter(new NewsSourceAdapter(newsSourceDataList, newsSourcePresenter::onItemClicked));
    }

    @Override
    public void openNewsArticles(NewsSourceData newsSourceData) {
        Intent intent = new Intent(NewsSourceActivity.this, NewsArticlesActivity.class);
        intent.putExtra(AppConfig.NEWS_SOURCE_ID, newsSourceData.getId());
        startActivity(intent);
    }
}
