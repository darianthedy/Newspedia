package com.dariand.newspedia.model;

import java.util.List;

public class NewsSourceContainerData {
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<NewsSourceData> getSources() {
        return sources;
    }

    public void setSources(List<NewsSourceData> sources) {
        this.sources = sources;
    }

    private String status;
    private List<NewsSourceData> sources;

    public NewsSourceContainerData() {

    }
}
