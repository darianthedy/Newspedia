package com.dariand.newspedia.model;

import java.util.List;

public class NewsArticlesContainerData {
    private String status;
    private int totalResult;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<NewsArticlesData> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsArticlesData> articles) {
        this.articles = articles;
    }

    private List<NewsArticlesData> articles;
}
