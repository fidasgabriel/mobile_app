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

    import com.example.culturallis.Model.Entity.CourseCard;
    import com.example.culturallis.Model.Entity.CourseViewHolder;
    import com.example.culturallis.R;

    import java.text.DecimalFormat;
    import java.util.List;

    public class CourseAdapter extends BaseAdapter {
        private Context applicationContext;
        private List<CourseCard> courseCard;
        private LayoutInflater inflater;

        public CourseAdapter(Context applicationContext, List<CourseCard> courseCard) {
            this.applicationContext = applicationContext;
            this.courseCard = courseCard;
            inflater = (LayoutInflater.from(applicationContext));
        }

        @Override
        public int getCount() {
            return courseCard.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            CourseViewHolder viewHolder;

            view = inflater.inflate(R.layout.course_list_view, null);
            viewHolder = new CourseViewHolder();
            viewHolder.likeButton = view.findViewById(R.id.like);
            viewHolder.courseImage = view.findViewById(R.id.courseImage);
            viewHolder.perfilImage = view.findViewById(R.id.perfilImage);
            viewHolder.courseTitle = view.findViewById(R.id.courseTitle);
            viewHolder.courseDescription = view.findViewById(R.id.courseDescription);
            view.setTag(viewHolder);

            CourseCard current = courseCard.get(i);
            viewHolder.courseImage.setImageResource(current.getCourseImage());
            viewHolder.perfilImage.setImageResource(current.getPerfilImage());

            String title = current.getCourseTitle();
            if (title.length() > 21){
                title = title.substring(0,21) + "...";
            }
            viewHolder.courseTitle.setText(title);

            if (current.isLiked()){
                animate(view,false,viewHolder.likeButton);
            }

            String attendingCount = String.valueOf(current.getViewsCount());
            if(current.getViewsCount() >= 1000000){
                DecimalFormat format = new DecimalFormat("0.#");
                attendingCount = format.format(current.getViewsCount() / 1000000.0) + " M";
            }else if(current.getViewsCount() >= 1000){
                DecimalFormat format = new DecimalFormat("0.#");
                attendingCount = format.format(current.getViewsCount() / 1000.0) + " mil";
            }
            viewHolder.courseDescription.setText(current.getCourseAuthor() + " º " + attendingCount + " cursados");

            viewHolder.likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    animate(view, current.isLiked(), viewHolder.likeButton);
                    current.setLiked();;
                }
            });
            return view;
        }

        public void animate(View view, boolean full, ImageView img)
        {
            AnimatedVectorDrawable drawable
                    = full
                    ? (AnimatedVectorDrawable) applicationContext.getDrawable(R.drawable.avd_heart_empty)
                    : (AnimatedVectorDrawable) applicationContext.getDrawable(R.drawable.avd_heart_fill);
            img.setImageDrawable(drawable);
            drawable.start();
        }
    }
