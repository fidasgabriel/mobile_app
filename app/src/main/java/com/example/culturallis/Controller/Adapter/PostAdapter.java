package com.example.culturallis.Controller.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.culturallis.Controller.Mutations.ToggleLikePost;
import com.example.culturallis.Controller.Mutations.ToggleSavePost;
import com.example.culturallis.Controller.Mutations.ToggleUnSavePost;
import com.example.culturallis.Controller.SqLite.UserDAO;
import com.example.culturallis.Model.Entity.PostCard;
import com.example.culturallis.Model.Usuario.Usuario;
import com.example.culturallis.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.Response;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<PostCard> postsCards;
    private boolean havePerfilImage;
    private Context context;
    private boolean isLarger;
    Usuario currentUser;
    private UserDAO userDAO;

    public PostAdapter(Context context){
        this.context = context;
        userDAO = new UserDAO(context);
    }

    public void setData(List<PostCard> postsCards, boolean havePerfilImage) {
        this.postsCards = postsCards;
        this.havePerfilImage = havePerfilImage;
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

        if(!post.getPostImage().startsWith("http")){
            byte[] decodedImagePost = Base64.decode(post.getPostImage(), Base64.DEFAULT);
            Bitmap imageBitmapPost = BitmapFactory.decodeByteArray(decodedImagePost, 0, decodedImagePost.length);

            Glide.with(holder.postImage.getContext())
                    .load(imageBitmapPost)
                    .into(holder.postImage);

        }else{
            Picasso.with(holder.postImage.getContext()).load(post.getPostImage()).into(holder.postImage);
        }

        if(!post.getPerfilImage().startsWith("http")){
            byte[] decocdeImageProfileOwner = Base64.decode(post.getPerfilImage(), Base64.DEFAULT);
            Bitmap imageBitmapProfileOwner = BitmapFactory.decodeByteArray(decocdeImageProfileOwner, 0, decocdeImageProfileOwner.length);

            Glide.with(holder.perfilImage.getContext())
                    .load(imageBitmapProfileOwner)
                    .into(holder.perfilImage);
        }else{
            Picasso.with(holder.perfilImage.getContext()).load(post.getPerfilImage()).into(holder.perfilImage);
        }

        String description = post.getDescription();
        if (description.length() > 162){
            String largerDescription = description;
            String reducedDescription = description.substring(0,162) + "...";
            description = reducedDescription;
            holder.postDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String desc = largerDescription;
                    if(isLarger) {
                        desc = reducedDescription;
                        isLarger = false;
                    }else{
                        isLarger = true;
                    }
                    holder.postDescription.setText(desc);
                }
            });
        }
        holder.postDescription.setText(description);

        holder.perfilUser.setText(post.getPostAuthor());

        if (post.isLiked()){
            animate(false, holder.likeButton, holder.itemView.getContext());
        }

        if(post.isSaved()){
            toSave(false, holder.saveButton,holder.itemView.getContext());
        }

        String currentEmail = userDAO.getCurrentEmail();

        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animate(post.isLiked(), holder.likeButton,holder.itemView.getContext());
                post.setLiked(!post.isLiked());

                try {
                    currentUser = new Usuario();
                    currentUser.setEmail(currentEmail);
                    new PostAdapter.ToggleLikePosts().execute(post.getPk_id().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        holder.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSave(post.isSaved(), holder.saveButton,holder.itemView.getContext());
                    try {
                        currentUser = new Usuario();
                        currentUser.setEmail(currentEmail);
                        new PostAdapter.ToggleSavePosts().execute(post.getPk_id().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                post.setSaved(!post.isSaved());
            }
        });

        holder.postImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                animate(false, holder.likeButton, holder.itemView.getContext());
                post.setLiked(true);
                return false;
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
                ?  context.getDrawable(R.drawable.post_save_icon_empty)
                :  context.getDrawable(R.drawable.perfil_saved_icon);
        img.setImageDrawable(drawable);
        int purple =  ContextCompat.getColor(context, R.color.saved_icon_purple);
        img.setColorFilter(purple, PorterDuff.Mode.SRC_IN);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        private ImageView postImage;
        private ImageView perfilImage;

        private TextView perfilUser;
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
            perfilUser = itemView.findViewById(R.id.postAuthor);
            likeButton = itemView.findViewById(R.id.like);
            saveButton = itemView.findViewById(R.id.save);
        }
    }

    public static void removeChildFromParent(View view) {
        if ((view != null) && (view.getParent() != null) && view.getParent() instanceof ViewGroup) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    private class ToggleLikePosts extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            if (params.length != 1) {
                return false;
            }

            String pk_id_post = params[0];

            try {
                Response responseToggle = new ToggleLikePost().toggleLike(Long.valueOf(pk_id_post), currentUser.getEmail());
                if (responseToggle != null) {
                    if (responseToggle.isSuccessful()) {
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {
        }
    }

    private class ToggleSavePosts extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            if (params.length != 1) {
                return false;
            }

            String pk_id_post = params[0];

            try {
                Response responseToggle = new ToggleSavePost().toggleSave(Long.valueOf(pk_id_post), currentUser.getEmail());
                if (responseToggle != null) {
                    if (responseToggle.isSuccessful()) {
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {
        }
    }
}
