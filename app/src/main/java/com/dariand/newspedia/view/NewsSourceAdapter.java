package com.dariand.newspedia.view;

import android.net.sip.SipSession;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dariand.newspedia.R;
import com.dariand.newspedia.model.NewsSourceData;

import java.util.List;

public class NewsSourceAdapter extends RecyclerView.Adapter<NewsSourceAdapter.NewsSourceHolder> {

    private List<NewsSourceData> newsSourceDataList;
    private Listener listener;

    public interface Listener {
        void onItemClicked(NewsSourceData item);
    }

    public NewsSourceAdapter(List<NewsSourceData> newsSourceDataList, Listener listener) {
        this.newsSourceDataList = newsSourceDataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsSourceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_news_source_data, viewGroup, false);
        return new NewsSourceHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsSourceHolder newsListHolder, int i) {
        NewsSourceData newsSourceData = newsSourceDataList.get(i);
        newsListHolder.setItemContent(newsSourceData);
        newsListHolder.newsListAdapterLinearLayout.setOnClickListener(v -> listener.onItemClicked(newsSourceData));
    }

    @Override
    public int getItemCount() {
        return newsSourceDataList.size();
    }

    static class NewsSourceHolder extends RecyclerView.ViewHolder {
        LinearLayout newsListAdapterLinearLayout;

        TextView txtNewsSourceName;
        TextView txtNewsSourceDescription;

        NewsSourceHolder(LinearLayout newsListAdapterLinearLayout) {
            super(newsListAdapterLinearLayout);
            this.newsListAdapterLinearLayout = newsListAdapterLinearLayout;

            txtNewsSourceName = newsListAdapterLinearLayout.findViewById(R.id.txtNewsSourceName);
            txtNewsSourceDescription = newsListAdapterLinearLayout.findViewById(R.id.txtNewsSourceDescription);
        }

        void setItemContent(NewsSourceData newsSourceData) {
            txtNewsSourceName.setText(newsSourceData.getName());
            txtNewsSourceDescription.setText(newsSourceData.getDescription());
        }
    }
}
