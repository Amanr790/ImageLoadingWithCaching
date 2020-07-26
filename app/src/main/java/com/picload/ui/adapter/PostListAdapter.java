package com.picload.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greedy.imageloader.ImageLoader;
import com.greedy.imageloader.models.ResizeOptions;
import com.picload.R;
import com.picload.data.model.Child;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder> {

    private final ImageClickListener listener;
    private List<Child> children;

    public PostListAdapter(ImageClickListener listener) {
        children = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_images, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        fetchImage(children.get(position).getData().getUrl(), holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    public void submitList(List<Child> children) {
        this.children = children;
        notifyDataSetChanged();
    }

    /**
     * Fetch image from the network or cache.
     */
    private void fetchImage(String url, AppCompatImageView imageView) {
        ImageLoader.ImageLoaderWrapper wrapper = new ImageLoader.ImageLoaderWrapper();
        wrapper.with(imageView.getContext())
                .load(url)
                .resize(new ResizeOptions(500, 500))
                .placeholder(ContextCompat.getDrawable(imageView.getContext(), R.drawable.ic_launcher_foreground))
                .into(imageView);

    }

    public interface ImageClickListener {
        void onImageClick(String url);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_image)
        AppCompatImageView ivImage;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ivImage.setOnClickListener(v -> {
                if (getAdapterPosition() != -1 && listener != null) {
                    listener.onImageClick(children.get(getAdapterPosition()).getData().getUrl());
                }
            });
        }
    }
}
