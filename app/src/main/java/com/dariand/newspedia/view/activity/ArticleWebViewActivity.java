package com.dariand.newspedia.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;

import com.dariand.newspedia.R;
import com.dariand.newspedia.config.AppConfig;
import com.dariand.newspedia.presenter.ArticleWebViewPresenter;
import com.dariand.newspedia.view.ArticleWebView;
import com.dariand.newspedia.view.ArticleWebViewClient;

public class ArticleWebViewActivity extends AppCompatActivity implements ArticleWebView {

    private WebView wvArticle;
//    private Toolbar toolbar;

    private ArticleWebViewPresenter articleWebViewPresenter;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_article_web_view);

        articleWebViewPresenter = new ArticleWebViewPresenter(this);

        if(savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                url = null;
            }
            else {
                url = extras.getString(AppConfig.NEWS_ARTICLE_URL);
            }
        }
        else {
            url = (String)savedInstanceState.getSerializable(AppConfig.NEWS_ARTICLE_URL);
        }

        initializeView();

        articleWebViewPresenter.onCreate();
    }

    @Override
    protected void onStop() {
        articleWebViewPresenter.onStop();
        super.onStop();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void initializeView() {
        wvArticle = (WebView)findViewById(R.id.wvArticle);
        wvArticle.getSettings().setJavaScriptEnabled(true);
        wvArticle.setWebViewClient(new ArticleWebViewClient());

        wvArticle.setOnKeyListener((v, keyCode, event) -> {
            if ((keyCode == KeyEvent.KEYCODE_BACK) && wvArticle.canGoBack()) {
                wvArticle.goBack();
                return true;
            }
            return false;
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }

    @Override
    public void loadArticle() {
        wvArticle.loadUrl(url);
    }
}
