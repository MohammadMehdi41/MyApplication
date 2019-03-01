package com.example.admin.myapplication.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.admin.myapplication.DataFakeGenerator;
import com.example.admin.myapplication.DownloadImageTask;
import com.example.admin.myapplication.R;
import com.example.admin.myapplication.SevenLearnDatabaseOpenHelper;
import com.example.admin.myapplication.adapter.PostsAdapter;
import com.example.admin.myapplication.datamodel.Post;

import java.util.ArrayList;
import java.util.List;

public class PostsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Post> posts = new ArrayList<>();
    private Context context;
    private static final int REQUEST_PERMISSION_CODE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        setupRecyclerView();
        getPostsFromDatabase();


        //openHelper.addPosts(posts);
        PostsAdapter postsAdapter = new PostsAdapter(PostsActivity.this, DataFakeGenerator.getData(PostsActivity.this));

        recyclerView.setAdapter(postsAdapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                saveImagesInSdCard();
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
            }
        } else {
            saveImagesInSdCard();
        }

    }


    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(PostsActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    private void getPostsFromDatabase() {
        SevenLearnDatabaseOpenHelper databaseOpenHelper = new SevenLearnDatabaseOpenHelper(this);
        List<Post> posts = databaseOpenHelper.getPosts();
        PostsAdapter postsAdapter = new PostsAdapter(this, posts);
        recyclerView.setAdapter(postsAdapter);
    }

    private void saveImagesInSdCard() {
        List<String> urls = new ArrayList<>();

        DownloadImageTask downloadImageTask = new DownloadImageTask(this, urls);
        downloadImageTask.execute();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0) {
                saveImagesInSdCard();
            } else {
                Toast.makeText(this, "برای ذخیره سازی باید دسترسی لازم را بدهید.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
