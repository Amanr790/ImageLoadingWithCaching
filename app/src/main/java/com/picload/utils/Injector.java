package com.picload.utils;

import com.picload.data.DataManager;
import com.picload.ui.image.ImageListRepo;
import com.picload.ui.image.ViewModelFactory;

import androidx.lifecycle.ViewModelProvider;

public class Injector {

    private static Injector instance;

    public synchronized static Injector getInstance(){
        if (instance==null){
            instance=new Injector();
        }
        return instance;
    }


    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    public ViewModelProvider.Factory provideViewModelFactory() {
        return new ViewModelFactory(provideImageRepository());

    }

    /**
     * Creates an instance of [SearchRepository] with FilterProductHelperInstance
     */
    private ImageListRepo provideImageRepository() {
        return new ImageListRepo(DataManager.getInstance());
    }


}
