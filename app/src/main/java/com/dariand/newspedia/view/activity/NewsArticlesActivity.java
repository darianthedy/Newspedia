package com.dariand.newspedia.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.dariand.newspedia.R;
import com.dariand.newspedia.config.AppConfig;
import com.dariand.newspedia.model.NewsArticlesData;
import com.dariand.newspedia.model.NewsArticlesInteractor;
import com.dariand.newspedia.presenter.NewsArticlesPresenter;
import com.dariand.newspedia.view.NewsArticlesAdapter;
import com.dariand.newspedia.view.NewsArticlesView;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class NewsArticlesActivity extends AppCompatActivity implements NewsArticlesView {

    @Inject
    NewsArticlesInteractor newsArticlesInteractor;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private NewsArticlesPresenter newsArticlesPresenter;
    private String newsSourceId;
    private NewsArticlesAdapter newsArticlesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_articles);

        newsArticlesPresenter = new NewsArticlesPresenter(this, newsArticlesInteractor);
        initializeView();

        if(savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newsSourceId = null;
            }
            else {
                newsSourceId = extras.getString(AppConfig.NEWS_SOURCE_ID);
            }
        }
        else {
            newsSourceId = (String)savedInstanceState.getSerializable(AppConfig.NEWS_SOURCE_ID);
        }
    }

    @Override
    protected void onResume() {
        newsArticlesPresenter.onResume();
        super.onResume();
    }

    @Override
    protected void onStop() {
        newsArticlesPresenter.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        newsArticlesPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchArticle).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newsArticlesAdapter != null) {
                    newsArticlesAdapter.filter(newText);
                }
                return true;
            }
        });

        return true;
    }

    private void initializeView() {
        recyclerView = findViewById(R.id.newsArticlesRecyclerView);
        progressBar = findViewById(R.id.progress);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    public String getSourceId() {
        return this.newsSourceId;
    }

    @Override
    public void setNewsArticlesItem(List<NewsArticlesData> newsArticlesDataList) {
        newsArticlesAdapter = new NewsArticlesAdapter(newsArticlesDataList, newsArticlesPresenter::onItemClicked);
        recyclerView.setAdapter(newsArticlesAdapter);
    }

    @Override
    public void openArticle(NewsArticlesData newsArticlesData) {
        Intent intent = new Intent(NewsArticlesActivity.this, ArticleWebViewActivity.class);
        intent.putExtra(AppConfig.NEWS_ARTICLE_URL, newsArticlesData.getUrl());
        startActivity(intent);
    }
}
