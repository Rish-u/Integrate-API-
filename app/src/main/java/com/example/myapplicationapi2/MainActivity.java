package com.example.myapplicationapi2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationapi2.adapter.PhotoAdapter;
import com.example.myapplicationapi2.model.Photo;
import com.example.myapplicationapi2.network.ApiService;
import com.example.myapplicationapi2.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        apiService.getPhotos().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Photo>> call, @NonNull Response<List<Photo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Photo> photos = response.body();
                    photoAdapter = new PhotoAdapter(photos);
                    recyclerView.setAdapter(photoAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Photo>> call, @NonNull Throwable t) {
                // Handle error
            }
        });

        Button createPhotoButton = findViewById(R.id.create_photo_button);
        createPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPhoto();
            }
        });
    }

    private void createPhoto() {
        Photo newPhoto = new Photo();
        newPhoto.setAlbumId(1);
        newPhoto.setTitle("New Photo");
        newPhoto.setUrl("https://via.placeholder.com/600/92c952");
        newPhoto.setThumbnailUrl("https://via.placeholder.com/150/92c952");

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        apiService.createPhoto(newPhoto).enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(@NonNull Call<Photo> call, @NonNull Response<Photo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Photo createdPhoto = response.body();
                    // Add the new photo to the adapter's data set
                    photoAdapter.getPhotos().add(0, createdPhoto);
                    photoAdapter.notifyItemInserted(0);
                    recyclerView.scrollToPosition(0);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Photo> call, @NonNull Throwable t) {
                // Handle error
            }
        });
    }
}
