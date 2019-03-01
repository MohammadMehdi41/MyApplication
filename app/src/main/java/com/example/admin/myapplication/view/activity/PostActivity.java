package com.example.admin.myapplication.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.myapplication.DataFakeGenerator;
import com.example.admin.myapplication.R;
import com.example.admin.myapplication.SevenLearnDatabaseOpenHelper;
import com.example.admin.myapplication.adapter.PostsAdapter;
import com.example.admin.myapplication.datamodel.Post;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Post> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        setupRecyclerView();
        getPostsFromDatabase();

        posts = DataFakeGenerator.getData(this);

                PostsAdapter postsAdapter =new PostsAdapter(PostActivity.this,posts);
                recyclerView.setAdapter(postsAdapter);
            }




    private void setupRecyclerView(){
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(PostActivity.this,LinearLayoutManager.VERTICAL,false));
    }

    private void getPostsFromDatabase(){
        SevenLearnDatabaseOpenHelper databaseOpenHelper=new SevenLearnDatabaseOpenHelper(this);
        List<Post> posts=databaseOpenHelper.getPosts();
        PostsAdapter postsAdapter =new PostsAdapter(this,posts);
        recyclerView.setAdapter(postsAdapter);
    }
}
