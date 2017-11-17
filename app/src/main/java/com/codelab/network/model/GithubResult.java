package com.codelab.network.model;

/**
 * Created by Ashiq on 11/18/17.
 */
public class GithubResult {

    private String name;
    private String url;

    public GithubResult(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
