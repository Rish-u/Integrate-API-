package com.example.myapplicationapi2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationapi2.R;
import com.example.myapplicationapi2.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private List<Photo> photos;

    public PhotoAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.title.setText(photo.getTitle());
        Picasso.get().load(photo.getThumbnailUrl()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView thumbnail;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.photo_title);
            thumbnail = itemView.findViewById(R.id.photo_thumbnail);
        }
    }
}
