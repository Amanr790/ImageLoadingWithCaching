package com.picload.ui.image;

import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.os.Bundle;

import com.picload.utils.Constants;
import com.picload.R;
import com.picload.utils.Injector;
import com.picload.ui.adapter.PostListAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ImageListActivity extends AppCompatActivity implements ComponentCallbacks2, PostListAdapter.ImageClickListener {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private PostListAdapter adapter;
    private ImageListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        ButterKnife.bind(this);
        setUpRecyclerview();
        setUpViewModel();
    }

    private void setUpRecyclerview() {
        adapter = new PostListAdapter(this::onImageClick);
        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvList.setAdapter(adapter);
    }

    private void setUpViewModel() {
        viewModel = new ViewModelProvider(this, Injector.getInstance().provideViewModelFactory()).get(ImageListViewModel.class);
        viewModel.getImageList().observe(this, children -> adapter.submitList(children));
    }

    @Override
    public void onImageClick(String url) {
        Intent intent = new Intent(ImageListActivity.this, FullImageActivity.class);
        intent.putExtra(Constants.IMAGE_URL, url);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onTrimMemory(int level) {
        //cache.trimMemory(level);
    }

}
