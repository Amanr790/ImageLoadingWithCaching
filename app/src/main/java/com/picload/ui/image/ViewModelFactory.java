package com.picload.ui.image;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final ImageListRepo repo;

    public ViewModelFactory(ImageListRepo imageListRepo) {
        this.repo=imageListRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (ImageListViewModel.class.isAssignableFrom(modelClass)) {
            try {
                return (T) new ImageListViewModel(repo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.create(modelClass);
    }
}
