package com.codelab.network.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codelab.network.R;
import com.codelab.network.api.helper.RequestSearch;
import com.codelab.network.http.ResponseListener;
import com.codelab.network.model.GithubResult;
import com.codelab.network.utility.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    private EditText etSearchBox;
    private Button btnSearch;
    private TextView tvSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVars();
        initView();
        initFunctionality();
        initListeners();
    }

    private void initVars() {
        mContext = MainActivity.this.getApplicationContext();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        etSearchBox = (EditText) findViewById(R.id.et_search_box);
        btnSearch = (Button) findViewById(R.id.btn_search);
        tvSearchResult = (TextView) findViewById(R.id.tv_search_result);
    }

    private void initFunctionality() {
        if(!Utils.isNetworkAvailable(mContext)) {
            Utils.showToast(mContext, getString(R.string.no_internet));
        }
    }

    private void initListeners() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSearch(etSearchBox.getText().toString());
            }
        });
    }

    private void performSearch(String searchQuery) {
        if(searchQuery != null && !searchQuery.isEmpty()) {
            tvSearchResult.setText(getString(R.string.searching_data));
            RequestSearch requestSearch = new RequestSearch(mContext);
            requestSearch.buildParams(searchQuery);
            requestSearch.setResponseListener(new ResponseListener() {
                @Override
                public void onResponse(Object data) {
                    if(data != null) {
                        ArrayList<GithubResult> arrayList = (ArrayList<GithubResult>) data;

                        if(!arrayList.isEmpty()) {
                            tvSearchResult.setText("");
                            for (GithubResult githubResult : arrayList) {
                                tvSearchResult.append(githubResult.getName() + "\n");
                                tvSearchResult.append(githubResult.getUrl() + "\n\n\n");
                            }
                        } else {
                            tvSearchResult.setText(getString(R.string.no_result));
                        }
                    } else {
                        tvSearchResult.setText(getString(R.string.no_result));
                    }
                }
            });
            requestSearch.execute();
        } else {
            Utils.showToast(mContext, getString(R.string.type_something));
        }
    }
}
