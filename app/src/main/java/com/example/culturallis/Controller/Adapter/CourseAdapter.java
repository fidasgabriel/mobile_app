    package com.example.culturallis.Controller.Adapter;

    import android.content.Context;
    import android.graphics.drawable.AnimatedVectorDrawable;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseAdapter;
    import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.culturallis.Model.Entity.CourseCard;
    import com.example.culturallis.Model.Entity.CourseViewHolder;
    import com.example.culturallis.R;

    import java.text.DecimalFormat;
    import java.util.List;

    public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
        private List<CourseCard> coursesCards;

        public void setData(List<CourseCard> coursesCards) {
            this.coursesCards = coursesCards;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_view, parent, false);
            return new CourseViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
            CourseCard course = coursesCards.get(position);
            if (course == null){
                return;
            }
            holder.courseImage.setImageResource(course.getCourseImage());
            holder.perfilImage.setImageResource(course.getPerfilImage());
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
            holder.courseTitle.setText(course.getCourseAuthor()+ " º " + attendingCount + " cursados");

            if (course.isLiked()){
                animate(false, holder.likeButton, holder.itemView.getContext());
            }

            holder.likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    animate(course.isLiked(), holder.likeButton,holder.itemView.getContext());
                    course.setLiked();
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

//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//            CourseViewHolder viewHolder;
//
//            view = inflater.inflate(R.layout.course_list_view, null);
//            viewHolder = new CourseViewHolder();
//            viewHolder.likeButton = view.findViewById(R.id.like);
//            viewHolder.courseImage = view.findViewById(R.id.courseImage);
//            viewHolder.perfilImage = view.findViewById(R.id.perfilImage);
//            viewHolder.courseTitle = view.findViewById(R.id.courseTitle);
//            viewHolder.courseDescription = view.findViewById(R.id.courseDescription);
//            view.setTag(viewHolder);
//
//            CourseCard current = courseCard.get(i);
//            viewHolder.courseImage.setImageResource(current.getCourseImage());
//            viewHolder.perfilImage.setImageResource(current.getPerfilImage());
//
//            String title = current.getCourseTitle();
//            if (title.length() > 21){
//                title = title.substring(0,21) + "...";
//            }
//            viewHolder.courseTitle.setText(title);
//
//            if (current.isLiked()){
//                animate(view,false,viewHolder.likeButton);
//            }
//
//            String attendingCount = String.valueOf(current.getViewsCount());
//            if(current.getViewsCount() >= 1000000){
//                DecimalFormat format = new DecimalFormat("0.#");
//                attendingCount = format.format(current.getViewsCount() / 1000000.0) + " M";
//            }else if(current.getViewsCount() >= 1000){
//                DecimalFormat format = new DecimalFormat("0.#");
//                attendingCount = format.format(current.getViewsCount() / 1000.0) + " mil";
//            }
//            viewHolder.courseDescription.setText(current.getCourseAuthor() + " º " + attendingCount + " cursados");
//
//            viewHolder.likeButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    animate(view, current.isLiked(), viewHolder.likeButton);
//                    current.setLiked();;
//                }
//            });
//            return view;
//        }

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
            private ImageView courseImage;
            private ImageView perfilImage;
            private TextView courseTitle;
            private TextView courseDescription;
            private ImageView likeButton;

            public CourseViewHolder(@NonNull View itemView) {
                super(itemView);

                courseImage = itemView.findViewById(R.id.courseImage);
                perfilImage = itemView.findViewById(R.id.perfilImage);
                courseTitle = itemView.findViewById(R.id.courseTitle);
                courseDescription = itemView.findViewById(R.id.courseDescription);
                likeButton = itemView.findViewById(R.id.like);
            }
        }
    }
