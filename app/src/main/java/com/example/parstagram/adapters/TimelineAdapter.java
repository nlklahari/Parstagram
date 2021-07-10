package com.example.parstagram.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.parstagram.activities.PostDetailsActivity;
import com.example.parstagram.models.Post;
import com.example.parstagram.R;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public TimelineAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        private ImageView ivProfile;
        private TextView tvTopUsername;
        private ImageView ivImage;
        private TextView tvBelowUsername;
        private TextView tvCaption;

        private ImageButton iBtnLike;
        private ImageButton iBtnComment;
        private ImageButton iBtnMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProfile = itemView.findViewById(R.id.ivProfile);
            tvTopUsername = itemView.findViewById(R.id.tvTopUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvBelowUsername = itemView.findViewById(R.id.tvBelowUsername);
            tvCaption = itemView.findViewById(R.id.tvCaption);

            iBtnLike = itemView.findViewById(R.id.iBtnLike);
            iBtnComment = itemView.findViewById(R.id.iBtnComment);
            iBtnMessage = itemView.findViewById(R.id.iBtnMessage);

            buttonClicks();


        }

        public void bind(Post post) {
            // TODO: Glide.with(context).load(post.getImage()).into(ivProfile);
            tvTopUsername.setText(post.getUser().getUsername());
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }
            tvBelowUsername.setText(post.getUser().getUsername());
            tvCaption.setText(post.getCaption());
            // TODO: Add decorator: https://guides.codepath.org/android/Using-the-RecyclerView#attaching-click-listeners-with-decorators


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(context, PostDetailsActivity.class);
                        intent.putExtra("post", post);
                        context.startActivity(intent);
                    }
                });
        }

        private void buttonClicks() {
            iBtnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Like", Toast.LENGTH_SHORT).show();
                }
            });

            iBtnComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Comment", Toast.LENGTH_SHORT).show();
                }
            });

            iBtnMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Message", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

}
