package org.goodev.retrofitdemo;

import java.util.List;

import org.goodev.retrofitdemo.GitHubClient.Contributor;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    ListView mListView;
    Callback<List<Contributor>> callback = new Callback<List<Contributor>>() {

        @Override
        public void success(List<Contributor> contributors, Response response) {
            ArrayAdapter<Contributor> adapter = new ArrayAdapter<GitHubClient.Contributor>(getApplicationContext(),
                    android.R.layout.simple_list_item_1, contributors);
            mListView.setAdapter(adapter);
        }

        @Override
        public void failure(RetrofitError error) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListView = getListView();
        GitHubClient.getContributors(callback);
    }

}
