package com.dariand.newspedia.model;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class RequestQueueSingleton {
    private static RequestQueueSingleton instance;
    private Context mContext;
    private RequestQueue mRequestQueue;

    private RequestQueueSingleton(Context mContext) {
        this.mContext = mContext;
        this.mRequestQueue = getRequestQueue();
    }

    public static RequestQueueSingleton getInstance(Context mContext) {
        if(instance == null) {
            instance = new RequestQueueSingleton(mContext);
        }

        return instance;
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
