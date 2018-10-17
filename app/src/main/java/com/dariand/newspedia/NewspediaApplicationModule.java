package com.dariand.newspedia;

import com.dariand.newspedia.view.activity.ArticleWebViewActivity;
import com.dariand.newspedia.view.activity.NewsArticlesActivity;
import com.dariand.newspedia.view.activity.NewsSourceActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class NewspediaApplicationModule {
    @ContributesAndroidInjector
    abstract NewsSourceActivity contributeNewsSourceActivityInjector();

    @ContributesAndroidInjector
    abstract NewsArticlesActivity contributeNewsArticlesActivityInjector();

    @ContributesAndroidInjector
    abstract ArticleWebViewActivity contributeArticleWebViewActivityInjector();
}
