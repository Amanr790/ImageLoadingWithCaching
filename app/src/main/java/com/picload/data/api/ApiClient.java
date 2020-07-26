package com.picload.data.api;

import com.picload.data.model.HotJson;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiClient {

    @GET("r/images/hot.json")
    Call<HotJson> getPostLists();
}
