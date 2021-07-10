package com.example.parstagram.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.parstagram.R;
import com.example.parstagram.models.Post;
import com.parse.ParseFile;

import org.parceler.Parcels;

public class PostDetailsActivity extends AppCompatActivity {

    private Post post;

    private ImageView ivProfile;
    private TextView tvTopUsername;
    private ImageView ivImage;
    private TextView tvNumOfLikes;
    private TextView tvBelowUsername;
    private TextView tvCaption;
    private TextView tvTimeStamp;

    private ImageButton iBtnLike;
    private ImageButton iBtnComment;
    private ImageButton iBtnMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        ivProfile = findViewById(R.id.ivProfile);
        tvTopUsername = findViewById(R.id.tvTopUsername);
        ivImage = findViewById(R.id.ivImage);
        tvNumOfLikes = findViewById(R.id.tvNumOfLikes);
        tvBelowUsername = findViewById(R.id.tvBelowUsername);
        tvCaption = findViewById(R.id.tvCaption);
        tvTimeStamp = findViewById(R.id.tvTimeStamp);

        iBtnLike = findViewById(R.id.iBtnLike);
        iBtnComment = findViewById(R.id.iBtnComment);
        iBtnMessage = findViewById(R.id.iBtnMessage);

        post = getIntent().getParcelableExtra("post");

        // TODO: Glide.with(context).load(post.getImage()).into(ivProfile);
        tvTopUsername.setText(post.getUser().getUsername());
        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(ivImage);
        }
        tvBelowUsername.setText(post.getUser().getUsername());
        tvCaption.setText(post.getCaption());
        tvTimeStamp.setText(Post.calculateTimeAgo(post.getCreatedAt()));
    }
}