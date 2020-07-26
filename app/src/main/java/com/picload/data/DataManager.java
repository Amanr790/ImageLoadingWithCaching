package com.picload.data;

import com.picload.data.api.ApiManager;
import com.picload.data.model.HotJson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager {


    private static DataManager dataManager;
    private final ApiManager apiManager;

    private DataManager(){
      apiManager= ApiManager.getInstance();
    }

    public static synchronized DataManager getInstance(){
        if (dataManager==null){
            dataManager=new DataManager();
        }
        return dataManager;
    }


    public Call<HotJson> getImageList() {
        return apiManager.getImageList();
    }
}
