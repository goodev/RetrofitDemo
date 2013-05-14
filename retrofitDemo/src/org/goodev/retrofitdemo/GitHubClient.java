/*
 * Copyright (C) 2012 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.goodev.retrofitdemo;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

public class GitHubClient {
    private static final String API_URL = "https://api.github.com";

    static class Contributor {
        String login;
        int contributions;

        @Override
        public String toString() {
            return login + ", " + contributions;
        }

    }

    interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(@Path("owner") String owner, @Path("repo") String repo);

        @GET("/repos/{owner}/{repo}/contributors")
        void contributors(@Path("owner") String owner, @Path("repo") String repo, Callback<List<Contributor>> callback);
    }

    public static void getContributors(Callback<List<Contributor>> callback) {
        // Create a very simple REST adapter which points the GitHub API
        // endpoint.
        RestAdapter restAdapter = new RestAdapter.Builder().setServer(API_URL).build();

        // Create an instance of our GitHub API interface.
        GitHub github = restAdapter.create(GitHub.class);

        // Fetch and print a list of the contributors to this library.
        github.contributors("square", "retrofit", callback);

    }
}
