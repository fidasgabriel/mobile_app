    package com.example.culturallis.Controller.Adapter;

    import android.content.Context;
    import android.content.Intent;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.graphics.drawable.AnimatedVectorDrawable;
    import android.os.AsyncTask;
    import android.util.Base64;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.cardview.widget.CardView;
    import androidx.recyclerview.widget.RecyclerView;

    import com.bumptech.glide.Glide;
    import com.example.culturallis.Controller.Mutations.ToggleLikeCourse;
    import com.example.culturallis.Model.Entity.CourseCard;
    import com.example.culturallis.Model.Usuario.Usuario;
    import com.example.culturallis.R;
    import com.example.culturallis.View.Skeletons.SkeletonCourseDetails;
    import com.squareup.picasso.Picasso;

    import java.text.DecimalFormat;
    import java.util.List;

    import okhttp3.Response;

    public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
        private List<CourseCard> coursesCards;
        private boolean havePerfilImage;
        Usuario currentUser;
        private Context context; // Adicione um campo para armazenar o contexto

        public CourseAdapter(Context context) {
            this.context = context;
        }

        public void setData(List<CourseCard> coursesCards, boolean havePerfilImage) {
            this.coursesCards = coursesCards;
            this.havePerfilImage = havePerfilImage;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_course, parent, false);
            return new CourseViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
            CourseCard course = coursesCards.get(position);
            if (course == null){
                return;
            }

            if(!course.getCourseImage().startsWith("http")){
                byte[] decodedImagePost = Base64.decode(course.getCourseImage(), Base64.DEFAULT);
                Bitmap imageBitmapPost = BitmapFactory.decodeByteArray(decodedImagePost, 0, decodedImagePost.length);

                Glide.with(holder.courseImage.getContext())
                        .load(imageBitmapPost)
                        .into(holder.courseImage);

            }else{
                Picasso.with(holder.courseImage.getContext()).load(course.getCourseImage()).into(holder.courseImage);
            }

            if(!course.getPerfilImage().startsWith("http")){
                byte[] decodedImagePost = Base64.decode(course.getPerfilImage(), Base64.DEFAULT);
                Bitmap imageBitmapPost = BitmapFactory.decodeByteArray(decodedImagePost, 0, decodedImagePost.length);

                Glide.with(holder.perfilImage.getContext())
                        .load(imageBitmapPost)
                        .into(holder.perfilImage);

            }else{
                Picasso.with(holder.perfilImage.getContext()).load(course.getPerfilImage()).into(holder.perfilImage);
            }

            String title = course.getCourseTitle();
            if (title.length() > 21){
                title = title.substring(0,21) + "...";
            }
            holder.courseTitle.setText(title);
            String attendingCount = String.valueOf(course.getViewsCount());
            if(course.getViewsCount() >= 1000000){
                DecimalFormat format = new DecimalFormat("0.#");
                attendingCount = format.format(course.getViewsCount() / 1000000.0) + " M";
            }else if(course.getViewsCount() >= 1000){
                DecimalFormat format = new DecimalFormat("0.#");
                attendingCount = format.format(course.getViewsCount() / 1000.0) + " mil";
            }
            holder.courseDescription.setText(course.getCourseAuthor()+ " º " + attendingCount + " cursados");

            if (course.isLiked()){
                animate(false, holder.likeButton, holder.itemView.getContext());
            }

            holder.likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    animate(course.isLiked(), holder.likeButton,holder.itemView.getContext());
                    course.setLiked();

                    try {
                        currentUser = new Usuario();
                        currentUser.setEmail("ana.damasceno@gmail.com");
                        new CourseAdapter.ToggleLikeCourses().execute(course.getPk_id().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            if (!havePerfilImage){
                removeChildFromParent((View) holder.imageCard);
                holder.courseDescription.setText(attendingCount + " cursados");
            }else{
                holder.courseDescription.setText(course.getCourseAuthor()+ " º " + attendingCount + " cursados");
            }

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SkeletonCourseDetails.class);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            if (coursesCards.size() != 0){
                return coursesCards.size();
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

        public class CourseViewHolder extends RecyclerView.ViewHolder {
            private CardView cardView;
            private ImageView courseImage;
            private ImageView perfilImage;
            private CardView imageCard;
            private TextView courseTitle;
            private TextView courseDescription;
            private ImageView likeButton;

            public CourseViewHolder(@NonNull View itemView) {
                super(itemView);

                courseImage = itemView.findViewById(R.id.courseImage);
                perfilImage = itemView.findViewById(R.id.perfilImage);
                imageCard = itemView.findViewById(R.id.imageCard);
                courseTitle = itemView.findViewById(R.id.courseTitle);
                courseDescription = itemView.findViewById(R.id.courseDescription);
                likeButton = itemView.findViewById(R.id.like);
                cardView = itemView.findViewById(R.id.rvCardView);
            }
        }

        public static void removeChildFromParent(View view) {
            if ((view != null) && (view.getParent() != null) && view.getParent() instanceof ViewGroup) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
        }

        private class ToggleLikeCourses extends AsyncTask<String, Void, Boolean> {
            @Override
            protected Boolean doInBackground(String... params) {
                if (params.length != 1) {
                    return false;
                }

                String pk_id_course = params[0];

                try {
                    Response responseToggle = new ToggleLikeCourse().toggleLikeCourse(Long.valueOf(pk_id_course), currentUser.getEmail());
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
