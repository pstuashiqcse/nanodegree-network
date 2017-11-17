package com.codelab.network.api.helper;

import android.content.Context;
import android.util.Log;

import com.codelab.network.api.params.HttpParams;
import com.codelab.network.api.parser.GithubDataParser;
import com.codelab.network.http.BaseHttp;
import com.codelab.network.http.ResponseListener;

import java.util.HashMap;

/**
 * Created by Ashiq on 11/18/17.
 */
public class RequestSearch extends BaseHttp {

    private Object object;
    private ResponseListener responseListener;

    public RequestSearch(Context context) {
        super(context, HttpParams.getRepositoriesUrl());
    }

    public void setResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    public void buildParams(String searchQuery) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(HttpParams.PARAM_QUERY, searchQuery);
        hashMap.put(HttpParams.PARAM_SORT, HttpParams.SORT_BY);

        setParams(hashMap, GET);
    }


    @Override
    public void onBackgroundTask(String response) {
        Log.d("Response", "Github JSON: " + response);
        object = new GithubDataParser().getData(response);
    }

    @Override
    public void onTaskComplete() {
        if (responseListener != null) {
            responseListener.onResponse(object);
        }
    }
}
