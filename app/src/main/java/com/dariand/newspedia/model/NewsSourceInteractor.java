package com.dariand.newspedia.model;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dariand.newspedia.config.AppConfig;
import com.dariand.newspedia.view.activity.NewsSourceActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class NewsSourceInteractor {

    public interface OnFinishedListener {
        void onFinished(List<NewsSourceData> items);
    }

//    public JsonObjectRequest getNewsSourceList(final OnFinishedListener listener) {
//        String url = AppConfig.NEWS_API + "sources?" + AppConfig.API_KEY;
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            List<NewsSourceData> newsSourceDataList = new ArrayList<NewsSourceData>();
//                            JSONArray newsSourceDataListJSON = response.getJSONArray("sources");
//
//                            if (newsSourceDataListJSON != null) {
//                                for(int i = 0; i < newsSourceDataListJSON.length(); i++) {
//                                    newsSourceDataList.add(getDataFromJSON((JSONObject)newsSourceDataListJSON.get(i)));
//                                }
//                            }
//
//                            listener.onFinished(newsSourceDataList);
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

    public void getNewsSourceList(final OnFinishedListener listener) {
        NewsAPIClient apiClient = ServiceGenerator.createService(NewsAPIClient.class);
        Call<NewsSourceContainerData> call = apiClient.getNewsSourceList();
        call.enqueue(new Callback<NewsSourceContainerData>() {
            @Override
            public void onResponse(Call<NewsSourceContainerData> call, retrofit2.Response<NewsSourceContainerData> response) {
                if (response.isSuccessful()) {
                    listener.onFinished(response.body().getSources());
                }
            }

            @Override
            public void onFailure(Call<NewsSourceContainerData> call, Throwable t) {
                // something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });
    }

    private NewsSourceData getDataFromJSON(JSONObject object) {
        NewsSourceData newsSourceData = new NewsSourceData();

        try {
            newsSourceData.setId(object.getString("id"));
            newsSourceData.setName(object.getString("name"));
            newsSourceData.setDescription(object.getString("description"));
            newsSourceData.setUrl(object.getString("url"));
            newsSourceData.setCategory(object.getString("category"));
            newsSourceData.setLanguage(object.getString("language"));
            newsSourceData.setCountry(object.getString("country"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return newsSourceData;
    }

    @Inject
    public NewsSourceInteractor() {

    }
}
