package com.dariand.newspedia.view;

import android.content.Context;

import com.dariand.newspedia.model.NewsSourceData;

import java.util.List;

public interface NewsSourceView {

    void showProgress();
    void hideProgress();
    Context getContext();

    void setNewsSourceItem(List<NewsSourceData> newsSourceDataList);
    void openNewsArticles(NewsSourceData newsSourceData);
}
