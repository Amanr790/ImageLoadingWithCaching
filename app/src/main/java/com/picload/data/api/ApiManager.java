package com.picload.data.api;

import com.google.gson.GsonBuilder;
import com.picload.utils.Constants;
import com.picload.data.model.HotJson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static ApiManager instance;
    private final OkHttpClient.Builder httpClient;
    private final ApiClient apiClient;
    private Retrofit.Builder retrofitBuilder;


    private ApiManager() {
        httpClient = getHttpClient();
        apiClient = getRetrofitService();
    }

    public static synchronized ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }
        return instance;
    }

    private ApiClient getRetrofitService() {
        retrofitBuilder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()));
        Retrofit retrofit = retrofitBuilder.client(httpClient.build()).build();
        return retrofit.create(ApiClient.class);
    }

    private OkHttpClient.Builder getHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(35000, TimeUnit.MILLISECONDS)
                .writeTimeout(20000, TimeUnit.MILLISECONDS);
        return builder;
    }

    public Call<HotJson> getImageList() {
        return apiClient.getPostLists();
    }
}
