package com.dariand.newspedia.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dariand.newspedia.R;
import com.dariand.newspedia.model.NewsArticlesData;
import com.dariand.newspedia.model.NewsSourceData;

import java.util.ArrayList;
import java.util.List;

public class NewsArticlesAdapter extends RecyclerView.Adapter<NewsArticlesAdapter.NewsArticlesHolder> {

    private List<NewsArticlesData> newsArticlesDataList;
    private List<NewsArticlesData> allNewsArticlesDataList;
    private NewsArticlesAdapter.Listener listener;

    public interface Listener {
        void onItemClicked(NewsArticlesData item);
    }

    public NewsArticlesAdapter(List<NewsArticlesData> newsArticlesDataList, NewsArticlesAdapter.Listener listener) {
        this.newsArticlesDataList = newsArticlesDataList;
        this.allNewsArticlesDataList = new ArrayList<NewsArticlesData>();
        this.allNewsArticlesDataList.addAll(newsArticlesDataList);
        this.listener = listener;
    }

    public void filter(String text) {
        this.newsArticlesDataList.clear();

        if(text.length() == 0) {
            this.newsArticlesDataList.addAll(allNewsArticlesDataList);
        }
        else {
            for(NewsArticlesData data : allNewsArticlesDataList) {
                if(data.getTitle().contains(text)) {
                    this.newsArticlesDataList.add(data);
                }
            }
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsArticlesAdapter.NewsArticlesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_news_articles_data, viewGroup, false);
        return new NewsArticlesHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsArticlesHolder newsArticlesHolder, int i) {
        NewsArticlesData newsArticlesData= newsArticlesDataList.get(i);
        newsArticlesHolder.setItemContent(newsArticlesData);
        newsArticlesHolder.newsArticlesAdapterLinearLayout.setOnClickListener(v -> listener.onItemClicked(newsArticlesData));
    }

    @Override
    public int getItemCount() {
        return newsArticlesDataList.size();
    }

    static class NewsArticlesHolder extends RecyclerView.ViewHolder {
        LinearLayout newsArticlesAdapterLinearLayout;

        TextView txtNewsArticlesTitle;
        TextView txtNewsArticlesAuthor;
        TextView txtNewsArticlesDescription;

        NewsArticlesHolder(LinearLayout newsArticlesAdapterLinearLayout) {
            super(newsArticlesAdapterLinearLayout);
            this.newsArticlesAdapterLinearLayout = newsArticlesAdapterLinearLayout;

            txtNewsArticlesTitle = newsArticlesAdapterLinearLayout.findViewById(R.id.txtNewsArticlesTitle);
            txtNewsArticlesAuthor = newsArticlesAdapterLinearLayout.findViewById(R.id.txtNewsArticlesAuthor);
            txtNewsArticlesDescription = newsArticlesAdapterLinearLayout.findViewById(R.id.txtNewsArticlesDescription);
        }

        void setItemContent(NewsArticlesData newsArticlesData) {
            txtNewsArticlesTitle.setText(newsArticlesData.getTitle());
            txtNewsArticlesAuthor.setText("By: " + newsArticlesData.getAuthor());
            txtNewsArticlesDescription.setText(newsArticlesData.getDescription());

            if(newsArticlesData.getAuthor() == null || newsArticlesData.getAuthor().isEmpty()) {
                txtNewsArticlesAuthor.setVisibility(ViewGroup.INVISIBLE);
            }
        }
    }
}
