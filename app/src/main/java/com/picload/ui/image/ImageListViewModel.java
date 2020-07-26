package com.picload.ui.image;

import com.picload.data.model.Child;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ImageListViewModel extends ViewModel {
    private final ImageListRepo repo;

    public ImageListViewModel(ImageListRepo repo) {
        this.repo=repo;
    }

    public MutableLiveData<List<Child>> getImageList() {
       return repo.getImageList();
    }
}
