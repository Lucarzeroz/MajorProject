package com.example.majorproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context mCtx;
    private List<Posts> postsList;

    public PostAdapter(Context mCtx, List<Posts> postsList) {
        this.mCtx = mCtx;
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mCtx);
        View view = layoutInflater.inflate(R.layout.post_layout, null);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Posts posts = postsList.get(position);
        holder.username.setText(posts.getUsername());
        holder.time.setText(posts.getTime());
        holder.title.setText(posts.getTitle());
        holder.postImage.setImageDrawable(mCtx.getResources().getDrawable(posts.getPostImage(), null));
        holder.commentbtn.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.ic_send));
        holder.comment.setText((CharSequence) posts.getComment());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        TextView username, time, title, content;
        ImageView postImage, commentbtn;
        TextInputEditText comment;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.usernametodisplay);
            time = itemView.findViewById(R.id.timeText);
            title = itemView.findViewById(R.id.titleText);
            content = itemView.findViewById(R.id.contentText);
            postImage = itemView.findViewById(R.id.postImage);
            commentbtn = itemView.findViewById(R.id.sendCommentBtn);
            comment = itemView.findViewById(R.id.CommentInput);
        }
    }

}
