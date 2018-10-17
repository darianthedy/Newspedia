package com.dariand.newspedia.model;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dariand.newspedia.config.AppConfig;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class NewsArticlesInteractor {
    public interface OnFinishedListener {
        void onFinished(List<NewsArticlesData> items);
    }

//    public JsonObjectRequest getNewsArticlesList(String sourceId, final NewsArticlesInteractor.OnFinishedListener listener) {
//        String url = AppConfig.NEWS_API + "everything?"
//                        + AppConfig.NEWS_SOURCES + "=" + sourceId + "&"
//                        + AppConfig.API_KEY;
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            List<NewsArticlesData> newsArticlesDataList = new ArrayList<NewsArticlesData>();
//                            JSONArray newsArticlesDataListJSON = response.getJSONArray("articles");
//
//                            if (newsArticlesDataListJSON != null) {
//                                for(int i = 0; i < newsArticlesDataListJSON.length(); i++) {
//                                    newsArticlesDataList.add(getDataFromJSON((JSONObject)newsArticlesDataListJSON.get(i)));
//                                }
//                            }
//
//                            listener.onFinished(newsArticlesDataList);
//                        }
//                        catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // TODO: Handle error
//                    }
//                });
//
//        return jsonObjectRequest;
//    }

    public void getNewsArticlesList(String source, final OnFinishedListener listener) {
        NewsAPIClient apiClient = ServiceGenerator.createService(NewsAPIClient.class);
        Call<NewsArticlesContainerData> call = apiClient.getNewsArticles(source);
        call.enqueue(new Callback<NewsArticlesContainerData>() {
            @Override
            public void onResponse(Call<NewsArticlesContainerData> call, retrofit2.Response<NewsArticlesContainerData> response) {
                if (response.isSuccessful()) {
                    listener.onFinished(response.body().getArticles());
                }
            }

            @Override
            public void onFailure(Call<NewsArticlesContainerData> call, Throwable t) {
                // something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });
    }

    private NewsArticlesData getDataFromJSON(JSONObject object) {
        NewsArticlesData newsArticlesData = new NewsArticlesData();

        try {
            JSONObject sourceJSON = object.getJSONObject("source");
            newsArticlesData.setSource(sourceJSON.getString("id"), sourceJSON.getString("name"));
            newsArticlesData.setAuthor(object.getString("author"));
            newsArticlesData.setTitle(object.getString("title"));
            newsArticlesData.setDescription(object.getString("description"));
            newsArticlesData.setUrl(object.getString("url"));
            newsArticlesData.setUrlToImage(object.getString("urlToImage"));
            newsArticlesData.setPublishedAt(object.getString("publishedAt"));
            newsArticlesData.setContent(object.getString("content"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return newsArticlesData;
    }

    @Inject
    public NewsArticlesInteractor() {

    }
}
