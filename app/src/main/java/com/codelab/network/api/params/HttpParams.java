package com.codelab.network.api.params;

/**
 * Created by Ashiq on 11/18/17.
 */
public class HttpParams {

    private static final String BASE_URL = "https://api.github.com/search/";

    private static final String API_REPOSITORIES = "repositories";
    public static String getRepositoriesUrl() {
        return BASE_URL + API_REPOSITORIES;
    }

    public final static String PARAM_QUERY = "q";
    public final static String PARAM_SORT = "sort";
    public final static String SORT_BY = "stars";

}
