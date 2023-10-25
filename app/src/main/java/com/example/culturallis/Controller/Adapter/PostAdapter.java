package com.example.culturallis.Controller.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.culturallis.Model.Entity.CourseCard;
import com.example.culturallis.Model.Entity.PostCard;
import com.example.culturallis.R;
import com.example.culturallis.View.Skeletons.SkeletonCourseDetails;

import java.text.DecimalFormat;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<PostCard> postsCards;
    private boolean havePerfilImage;
    private Context context;

    public PostAdapter(Context context){
        this.context = context;
    }

    public void setData(List<PostCard> postsCards, boolean havePerfilImage) {
        this.postsCards = postsCards;
        this.havePerfilImage = havePerfilImage;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostCard post = postsCards.get(position);
        if (post == null){
            return;
        }
        holder.postImage.setImageResource(post.getPostImage());
        holder.perfilImage.setImageResource(post.getPerfilImage());
        holder.postDescription.setText(post.getDescription());

        if (post.isLiked()){
            animate(false, holder.likeButton, holder.itemView.getContext());
        }

        if(post.isSaved()){
            toSave(true, holder.saveButton,holder.itemView.getContext());
        }

        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animate(post.isLiked(), holder.likeButton,holder.itemView.getContext());
                post.setLiked();
            }
        });

        holder.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSave(post.isSaved(), holder.saveButton,holder.itemView.getContext());
                post.setSaved();
            }
        });

        if (!havePerfilImage) {
            removeChildFromParent((View) holder.imageCard);
        }

        int purple =  ContextCompat.getColor(context, R.color.saved_icon_purple);
        holder.saveButton.setColorFilter(purple, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public int getItemCount() {
        if (postsCards.size() != 0){
            return postsCards.size();
        }
        return 0;
    }
    public void animate(boolean full, ImageView img, Context context)
    {
        AnimatedVectorDrawable drawable
                = full
                ? (AnimatedVectorDrawable) context.getDrawable(R.drawable.avd_heart_empty)
                : (AnimatedVectorDrawable) context.getDrawable(R.drawable.avd_heart_fill);
        img.setImageDrawable(drawable);
        drawable.start();
    }

    public void toSave(boolean isSaved,ImageView img, Context context)
    {
        Drawable drawable
                = isSaved
                ?  context.getDrawable(R.drawable.perfil_saved_icon)
                :  context.getDrawable(R.drawable.post_save_icon_empty);
        img.setImageDrawable(drawable);
        int purple =  ContextCompat.getColor(context, R.color.saved_icon_purple);
        img.setColorFilter(purple, PorterDuff.Mode.SRC_IN);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        private ImageView postImage;
        private ImageView perfilImage;
        private CardView imageCard;
        private TextView postDescription;
        private ImageView likeButton;
        private ImageView saveButton;

        public PostViewHolder(@NonNull View itemView){
            super(itemView);

            postImage = itemView.findViewById(R.id.postImage);
            perfilImage = itemView.findViewById(R.id.perfilImage);
            imageCard = itemView.findViewById(R.id.imageCard);
            postDescription = itemView.findViewById(R.id.postDescription);
            likeButton = itemView.findViewById(R.id.like);
            saveButton = itemView.findViewById(R.id.save);
        }
    }

    public static void removeChildFromParent(View view) {
        if ((view != null) && (view.getParent() != null) && view.getParent() instanceof ViewGroup) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }
}
