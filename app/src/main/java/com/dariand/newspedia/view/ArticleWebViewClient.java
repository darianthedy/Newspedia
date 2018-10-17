package com.dariand.newspedia.view;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ArticleWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }
}
