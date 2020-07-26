package com.picload.ui.image;

import android.os.Bundle;

import com.greedy.imageloader.ImageLoader;
import com.picload.utils.Constants;
import com.picload.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FullImageActivity extends AppCompatActivity {

    @BindView(R.id.iv_image)
    AppCompatImageView ivImage;
    private String imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        ButterKnife.bind(this);
        getUrl();
        showImage();
    }

    private void showImage() {
        ImageLoader.ImageLoaderWrapper wrapper = new ImageLoader.ImageLoaderWrapper();
        wrapper.load(imageUrl)
                .with(this)
                .placeholder(ContextCompat.getDrawable(this, R.drawable.ic_launcher_foreground))
                .into(ivImage);
    }

    private void getUrl() {
        if (getIntent() != null && getIntent().hasExtra(Constants.IMAGE_URL))
            imageUrl = getIntent().getStringExtra(Constants.IMAGE_URL);
    }

}
