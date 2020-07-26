package com.picload.ui.image;

import android.util.Log;

import com.picload.data.DataManager;
import com.picload.data.model.Child;
import com.picload.data.model.Data_;
import com.picload.data.model.HotJson;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ImageListRepo {
    private static String TAG = "ImageListRepo";
    private final DataManager datamanager;

    public ImageListRepo(DataManager dataManager) {
        this.datamanager = dataManager;
    }

    public MutableLiveData<List<Child>> getImageList() {
        final MutableLiveData<List<Child>> imageListLivedata = new MutableLiveData<>();
        datamanager.getImageList().enqueue(new Callback<HotJson>() {
            @Override
            public void onResponse(Call<HotJson> call, Response<HotJson> response) {
                if (response.body() != null && response.body().getData() != null && response.body().getData().getChildren() != null) {
                    imageListLivedata.setValue(response.body().getData().getChildren());
                }
            }

            @Override
            public void onFailure(Call<HotJson> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
        return imageListLivedata;
    }

}
