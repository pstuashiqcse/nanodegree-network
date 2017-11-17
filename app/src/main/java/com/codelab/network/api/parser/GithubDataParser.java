package com.codelab.network.api.parser;

import com.codelab.network.model.GithubResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ashiq on 11/18/17.
 */
public class GithubDataParser {

    public ArrayList<GithubResult> getData(String response) {
        try {
            ArrayList<GithubResult> arrayList = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject resultItem = jsonArray.getJSONObject(i);

                String name = null, url = null;
                if(resultItem.has("full_name")) {
                    name = resultItem.getString("full_name");
                }

                if(resultItem.has("html_url")) {
                    url = resultItem.getString("html_url");
                }

                arrayList.add(new GithubResult(name, url));
            }

            return arrayList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
