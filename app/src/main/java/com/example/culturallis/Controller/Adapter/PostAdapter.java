package com.example.culturallis.Controller.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.telecom.InCallService;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.culturallis.Controller.Mutations.ToggleLikePost;
import com.example.culturallis.Controller.Mutations.UpdateUser;
import com.example.culturallis.Model.Entity.CourseCard;
import com.example.culturallis.Model.Entity.PostCard;
import com.example.culturallis.Model.LikePostsAndCourses;
import com.example.culturallis.Model.Usuario.Usuario;
import com.example.culturallis.R;
import com.example.culturallis.View.Configuration.PerfilEdit;
import com.example.culturallis.View.Fragments.LoadingSettings;
import com.example.culturallis.View.Navbar.HomeScreen;
import com.example.culturallis.View.Navbar.PerfilCourseCreatorScreen;
import com.example.culturallis.View.Skeletons.SkeletonBlank;
import com.example.culturallis.View.Skeletons.SkeletonCourseDetails;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.Response;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<PostCard> postsCards;
    private boolean havePerfilImage;
    private Context context;

    LoadingSettings loadingDialog;
    Usuario currentUser;

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


        if(!post.getPostImage().startsWith("http")){
            byte[] decodedImagePost = Base64.decode(post.getPostImage(), Base64.DEFAULT);
            Bitmap imageBitmapPost = BitmapFactory.decodeByteArray(decodedImagePost, 0, decodedImagePost.length);

            Glide.with(holder.postImage.getContext())
                    .load(imageBitmapPost)
                    .into(holder.postImage);

        }else{
            Picasso.get().load(post.getPostImage()).into(holder.postImage);
        }

        if(!post.getPerfilImage().startsWith("http")){
            byte[] decocdeImageProfileOwner = Base64.decode(post.getPerfilImage(), Base64.DEFAULT);
            Bitmap imageBitmapProfileOwner = BitmapFactory.decodeByteArray(decocdeImageProfileOwner, 0, decocdeImageProfileOwner.length);

            Glide.with(holder.perfilImage.getContext())
                    .load(imageBitmapProfileOwner)
                    .into(holder.perfilImage);
        }else{
            Picasso.get().load(post.getPerfilImage()).into(holder.perfilImage);
        }

        holder.postDescription.setText(post.getDescription());
        holder.perfilUser.setText(post.getPostAuthor());

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

                try {
                    currentUser = new Usuario();
                    currentUser.setEmail("ana.damasceno@gmail.com");
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

            if (success) {
                Toast.makeText(context, "Sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Problemas ao realizar sua ação", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
