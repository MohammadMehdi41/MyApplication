package com.example.admin.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.SevenLearnDatabaseOpenHelper;
import com.example.admin.myapplication.datamodel.Post;
import com.example.admin.myapplication.view.activity.PostActivity;

import java.util.ArrayList;
import java.util.List;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.NewsViewHolder> {


    private Context context;
    private List<Post> posts=new ArrayList<>();

    public PostsAdapter(Context context ,List<Post> posts){
        this.context = context;
        this.posts = posts;
    }


    public void addPosts(List<Post> posts){
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    public void clear(){
        this.posts=new ArrayList<>();
        notifyDataSetChanged();
    }
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_post,parent,false);

        Typeface iranSansTypeface= Typeface.createFromAsset(context.getAssets(),"font/irannian_sans.ttf");
        return new NewsViewHolder(view,iranSansTypeface);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        final Post post=posts.get(position);
        /*
        Picasso.with(context).load(post.getPostImageUrl().replace("localhost","192.168.1.104")).into(holder.newsImage);
        */
//        Picasso.with(context).load(String.valueOf(post.getPostImageUrl())).into(holder.newsImage);

        holder.newsImage.setImageDrawable(post.getPostImageUrl());

        holder.title.setText(post.getTitle());
        holder.content.setText(post.getContent());
        holder.date.setText(post.getDate());

        if (post.getIsVisited()==1){
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.color_visited_post));
        }else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.color_not_visited_post));
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PostActivity.class);
                intent.putExtra(SevenLearnDatabaseOpenHelper.COL_ID,post.getId());
                intent.putExtra(SevenLearnDatabaseOpenHelper.COL_TITLE,post.getTitle());
                intent.putExtra(SevenLearnDatabaseOpenHelper.COL_CONTENT,post.getContent());



                intent.putExtra(SevenLearnDatabaseOpenHelper.COL_POST_IMAGE_URL, String.valueOf(post.getPostImageUrl()));
                intent.putExtra(SevenLearnDatabaseOpenHelper.COL_IS_VISITED,post.getIsVisited());
                intent.putExtra(SevenLearnDatabaseOpenHelper.COL_DATE,post.getDate());
                intent.putExtra("image_res_id", (CharSequence) post.getPostImageUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        private ImageView newsImage;
        private TextView title;
        private TextView content;
        private TextView date;

        public NewsViewHolder(View itemView, Typeface iranSansTypeface ) {
            super(itemView);
            newsImage=(ImageView)itemView.findViewById(R.id.item_image);
            title=(TextView)itemView.findViewById(R.id.news_title);
            title.setTypeface(iranSansTypeface);


            content=(TextView)itemView.findViewById(R.id.news_content);
            content.setTypeface(iranSansTypeface);

            date=(TextView)itemView.findViewById(R.id.news_date);
            date.setTypeface(iranSansTypeface);
        }
    }

    public static Bitmap toBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
